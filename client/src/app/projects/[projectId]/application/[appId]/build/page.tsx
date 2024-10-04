import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";
import { GitBranch } from "lucide-react";
const Page = () => {
  return (
    <div className="flex flex-col gap-4">
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
