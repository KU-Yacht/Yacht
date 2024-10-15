import { Construct } from 'constructs';
import { App, Chart, ChartProps } from 'cdk8s';
import {IntOrString, KubeDeployment, KubeService} from "./imports/k8s";
// const yaml = require('js-yaml');
// const fs = require('fs');

export class MyChart extends Chart {
  constructor(scope: Construct, id: string, props: ChartProps = { }) {
    super(scope, id, props);

    // define resources here
    const label = { app: 'hello-k8s' };

    new KubeService(this, 'service', {
      spec: {
        type: 'ClusterIP',
        ports: [ { port: 8080, targetPort: IntOrString.fromNumber(8080) } ],
        selector: label
      }
    });

    new KubeDeployment(this, 'deployment', {
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
                image: 'paulbouwer/hello-kubernetes:1.7',
                ports: [ { containerPort: 8080 } ]
              }
            ]
          }
        }
      }
    });

  }
}

// ts 이거 env받아와서 넣기
// const doc = yaml.load(fs.readFileSync('../.deploy/deploy.yaml', 'utf8'));

const app = new App();
new MyChart(app, "test");
app.synth();
