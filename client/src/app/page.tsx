import HeroImage from "@/components/hero-image";
import { Button } from "@/components/ui/button";
import { Handshake, Rocket, Wrench } from "lucide-react";

export default function Home() {
  return (
    <div className="p-10 flex justify-center items-center flex-1 flex-col gap-20">
      <HeroImage />
      <div className="flex flex-col md:flex-row gap-6 items-center">
        <div className="text-xl max-w-[600px]">
          <span className="font-extrabold">Yacht </span>
          <span className="text-gray-500">
            is a Kubernetes Build, Deployment, and Operations Automation
            Platform. You can easily deploy and manage applications through a
            user-friendly interface without needing any Kubernetes knowledge.
          </span>
        </div>
        <div className="flex gap-4">
          <Button>Get Started</Button>
          <Button variant="secondary">Learn More</Button>
        </div>
      </div>
      <div className="flex flex-col md:flex-row gap-8 items-center w-full justify-center">
        <div className="flex flex-col gap-4 border rounded-lg w-full max-w-[442px] h-[194px] p-6">
          <div className="flex gap-4 items-center">
            <Wrench />
            <h1 className="text-2xl font-semibold">Build</h1>
          </div>
          <p className="text-gray-500">
            Yacht provides a simple interface to build your applications. You
            can easily create a build configuration and start building your
            application.
          </p>
        </div>
        <div className="flex flex-col gap-4 border rounded-lg w-full max-w-[442px] h-[194px] p-6">
          <div className="flex gap-4 items-center">
            <Rocket />
            <h1 className="text-2xl font-semibold">Deploy</h1>
          </div>
          <p className="text-gray-500">
            Yacht allows you to deploy your applications to Kubernetes clusters
            with a single click. You can easily deploy your applications to
            different environments.
          </p>
        </div>
        <div className="flex flex-col gap-4 border rounded-lg w-full max-w-[442px] h-[194px] p-6">
          <div className="flex gap-4 items-center">
            <Handshake />
            <h1 className="text-2xl font-semibold">Operation</h1>
          </div>
          <p className="text-gray-500">
            Yacht provides a user-friendly interface to manage your
            applications. You can easily monitor your applications and manage
            their configurations.
          </p>
        </div>
      </div>
    </div>
  );
}
