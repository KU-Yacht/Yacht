"use client";

import { cn } from "@/lib/utils";
import Link from "next/link";
import { useParams, usePathname } from "next/navigation";

const AppNavbar = () => {
  const params = useParams();
  const pathname = usePathname();

  return (
    <div className="mt-10 flex items-center gap-8 md:gap-10">
      <Link
        href={`/projects/${params.projectId}/application/${params.appId}/overview`}
        className={cn(
          "text-xl font-semibold text-gray-500",
          pathname.split("/").pop() === "overview" &&
            "border-b-2 border-gray-900 text-gray-900 dark:border-gray-100 dark:text-gray-100",
        )}
      >
        Overview
      </Link>
      <Link
        href={`/projects/${params.projectId}/application/${params.appId}/build`}
        className={cn(
          "text-xl font-semibold text-gray-500",
          pathname.split("/").pop() === "build" &&
            "border-b-2 border-gray-900 text-gray-900 dark:border-gray-100 dark:text-gray-100",
        )}
      >
        Build
      </Link>
      <Link
        href={`/projects/${params.projectId}/application/${params.appId}/resources`}
        className={cn(
          "text-xl font-semibold text-gray-500",
          pathname.split("/").pop() === "resources" &&
            "border-b-2 border-gray-900 text-gray-900 dark:border-gray-100 dark:text-gray-100",
        )}
      >
        Resources
      </Link>
    </div>
  );
};

export default AppNavbar;
