import { Button } from "@/components/ui/button";
import { Edit, GitBranch, LayoutGrid, Rocket } from "lucide-react";
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";

const Page = ({ params }: { params: { appId: string; projectId: string } }) => {
  return (
    <div className="flex w-full flex-col gap-4 p-10">
      <h1 className="flex items-center gap-2 text-2xl font-bold md:text-3xl">
        <LayoutGrid className="inline-block" />
        Application {params.appId}
      </h1>

      <div className="flex w-full items-center justify-between">
        <h2 className="text-base font-semibold text-gray-500 md:text-xl">
          Project {params.projectId}
        </h2>
        <div className="flex items-center gap-4">
          <Button className="flex items-center gap-4" variant="secondary">
            <Edit />
            Edit
          </Button>
          <Button className="flex items-center gap-4">
            <Rocket />
            Deploy
          </Button>
        </div>
      </div>

      <h1 className="mt-10 flex items-center justify-end gap-2 text-sm font-bold md:text-xl">
        <GitBranch />
        <span>Latest commit : </span>
        <span className="text-gray-500">d6aad6</span>
      </h1>

      <Accordion type="single" collapsible className="w-full">
        <AccordionItem value="item-1">
          <AccordionTrigger className="text-lg md:text-xl">
            Prepare
          </AccordionTrigger>
          <AccordionContent>Prepare Logs</AccordionContent>
        </AccordionItem>
        <AccordionItem value="item-2">
          <AccordionTrigger className="text-lg md:text-xl">
            Build Logs
          </AccordionTrigger>
          <AccordionContent>Build Logs</AccordionContent>
        </AccordionItem>
        <AccordionItem value="item-3">
          <AccordionTrigger className="text-lg md:text-xl">
            Deployment Summary
          </AccordionTrigger>
          <AccordionContent>Deployment Summary</AccordionContent>
        </AccordionItem>
      </Accordion>
    </div>
  );
};

export default Page;
