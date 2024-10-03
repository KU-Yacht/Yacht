import { Button } from "@/components/ui/button";
import { Handshake, Rocket, Wrench } from "lucide-react";
import Image from "next/image";

export default function Home() {
  return (
    <>
      <div className="flex flex-1 flex-col items-center justify-center gap-20 p-10">
        <Image src="/images/hero.png" alt="Yacht" width={450} height={450} />
        <div className="flex flex-col items-center gap-6 md:flex-row">
          <div className="max-w-[600px] text-xl">
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
        <div className="flex w-full flex-col items-center justify-center gap-8 md:flex-row">
          <div className="flex min-h-[200px] w-full max-w-[442px] flex-col gap-4 rounded-lg border p-6">
            <div className="flex items-center gap-4">
              <Wrench />
              <h1 className="text-2xl font-semibold">Build</h1>
            </div>
            <p className="text-gray-500">
              Yacht provides a simple interface to build your applications. You
              can easily create a build configuration and start building your
              application.
            </p>
          </div>
          <div className="flex min-h-[200px] w-full max-w-[442px] flex-col gap-4 rounded-lg border p-6">
            <div className="flex items-center gap-4">
              <Rocket />
              <h1 className="text-2xl font-semibold">Deploy</h1>
            </div>
            <p className="text-gray-500">
              Yacht allows you to deploy your applications to Kubernetes
              clusters with a single click. You can easily deploy your
              applications to different environments.
            </p>
          </div>
          <div className="flex min-h-[200px] w-full max-w-[442px] flex-col gap-4 rounded-lg border p-6">
            <div className="flex items-center gap-4">
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
    </>
  );
}
