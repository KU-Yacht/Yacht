import { Construct } from 'constructs';
import { App, Chart, ChartProps, YamlOutputType } from 'cdk8s';
import {IntOrString, KubeDeployment, KubeService, KubeIngress} from "./imports/k8s";
// const yaml = require('js-yaml');
// const fs = require('fs');

export class MyChart extends Chart {
  constructor(scope: Construct, id: string, props: ChartProps = { }) {
    super(scope, id, props);

    // define resources here
    const label = { app: 'hello-k8s' };
    const appName = process.env.APP_NAME || 'default-app-name';

    new KubeService(this, 'service', {
      metadata: {
        name: "${appName}-service",
        namespace: "argo"
      },
      spec: {
        type: 'ClusterIP',
          ports: [{ port: 8080, targetPort: IntOrString.fromNumber(process.env.PORT) }],
        selector: label
      }
    });

    new KubeDeployment(this, 'deployment', {
      metadata: {
        name: "${appName}-deployment",
        namespace: "argo"
      },
      spec: {
        replicas: process.env.replicas,
        selector: {
          matchLabels: label
        },
        template: {
          metadata: { labels: label },
          spec: {
            containers: [
              {
                name: 'hello-kubernetes',
                image: process.env.DOCKER_IMAGE,
                ports: [ { containerPort: process.env.PORT } ]
              }
            ]
          }
        }
      }
    });
      
    new KubeIngress(this, 'ingress', {
      metadata: {
        name: '${appName}-ingress',
        namespace: 'argo',
        annotations: {
          'nginx.ingress.kubernetes.io/backend-protocol': 'HTTP',
          'nginx.ingress.kubernetes.io/force-ssl-redirect': 'true',
          'nginx.ingress.kubernetes.io/rewrite-target': '/',
          'nginx.ingress.kubernetes.io/ssl-passthrough': 'true',
        },
      },
      spec: {
        ingressClassName: 'nginx',
        rules: [
          {
            http: {
              paths: [
                {
                  path: '/${appName}(/|$)(.*)',
                  pathType: 'Prefix',
                  backend: {
                    service: {
                      name: '${appName}-service',
                      port: { number: 8080 },
                    },
                  },
                },
              ],
            },
          },
        ],
      },
    });

  }
}

const app = new App({
  yamlOutputType: YamlOutputType.FILE_PER_RESOURCE,
});
new MyChart(app, "test");
app.synth();
