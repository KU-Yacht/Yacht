import { Construct } from 'constructs';
import { App, Chart, ChartProps, YamlOutputType } from 'cdk8s';
import {IntOrString, KubeDeployment, KubeService} from "./imports/k8s";
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
        type: 'NodePort',
        ports: [ { port: 8080, targetPort: IntOrString.fromNumber(8080), nodePort: 30007 } ],
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

  }
}

const app = new App({
  yamlOutputType: YamlOutputType.FILE_PER_RESOURCE,
});
new MyChart(app, "test");
app.synth();
