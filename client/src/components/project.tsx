"use client";

import { cn } from "@/lib/utils";
import { ChevronDown, LayoutGrid } from "lucide-react";
import Link from "next/link";
import { useParams } from "next/navigation";
import { useState } from "react";

interface ProjectProps {
  data: {
    id: number;
    name: string;
    applications: {
      id: number;
      name: string;
      description: string;
    }[];
  };
}

const Project = ({ data }: ProjectProps) => {
  const [isOpen, setIsOpen] = useState(false);
  const params = useParams();

  return (
    <div className="flex flex-col gap-4">
      <div className="flex cursor-pointer items-center justify-center gap-2 md:justify-normal">
        <ChevronDown
          onClick={() => setIsOpen((prev) => !prev)}
          size={20}
          className={cn(
            "transition-transform duration-300 ease-in-out",
            !isOpen && "rotate-180 transform",
          )}
        />
        <Link href={`/projects/${data.id}`}>
          <h2 className="text-xl font-bold">{data.name}</h2>
        </Link>
      </div>

      <div className={cn("flex flex-col gap-2", isOpen ? "block" : "hidden")}>
        {data.applications.map((application) => (
          <Link
            key={application.id}
            href={`/projects/${data.id}/application/${application.id}`}
          >
            <div
              className={cn(
                "hover:text-bg-gray-700 flex w-full cursor-pointer items-center justify-center gap-2 rounded-md p-2 text-gray-500 transition-all duration-300 ease-in-out hover:bg-gray-200 dark:hover:bg-gray-800 dark:hover:text-gray-300 md:justify-normal",
                params.appId === application.id.toString() &&
                  "text-bg-gray-700 bg-gray-200 dark:bg-gray-800",
              )}
            >
              <LayoutGrid size={16} />
              <h3>{application.name}</h3>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default Project;
