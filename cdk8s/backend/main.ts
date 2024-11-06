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

    new KubeService(this, 'service', {
      metadata: {
        name: "spring-service",
        namespace: "argo"
      },
      spec: {
        type: 'ClusterIP',
          ports: [{ port: 8080, targetPort: IntOrString.fromNumber(8080) }],
        selector: label
      }
    });

    new KubeDeployment(this, 'deployment', {
      metadata: {
        name: "spring-deployment",
        namespace: "argo"
      },
      spec: {
        replicas: 1,
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
                ports: [ { containerPort: 8080 } ]
              }
            ]
          }
        }
      }
    });
      
    new KubeIngress(this, 'ingress', {
      metadata: {
        name: 'spring-ingress',
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
                  path: '/spring(/|$)(.*)',
                  pathType: 'Prefix',
                  backend: {
                    service: {
                      name: 'spring-service',
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
