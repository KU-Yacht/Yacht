"use client";

import { cn } from "@/lib/utils";
import { PanelLeftClose, PanelLeftOpen } from "lucide-react";
import { ReactNode, useState } from "react";
import AddProjectDialog from "./add-project-dialog";

const Sidebar = ({ children }: { children: ReactNode }) => {
  const [open, setOpen] = useState(true);

  return (
    <div
      className={cn(
        "flex flex-col items-center p-4 transition-all duration-300 ease-in-out",
        open ? "w-full md:w-[250px]" : "w-full md:w-[50px]",
      )}
    >
      <div className="flex w-full justify-end border-b pb-4">
        {open ? (
          <PanelLeftClose
            onClick={() => setOpen(false)}
            className={"-rotate-90 transform cursor-pointer md:rotate-0"}
          />
        ) : (
          <PanelLeftOpen
            onClick={() => setOpen(true)}
            className="-rotate-90 transform cursor-pointer md:rotate-0"
          />
        )}
      </div>

      <div
        className={cn(
          "max-h-screen overflow-auto",
          open ? "w-[250px]" : "hidden",
        )}
      >
        {children}
      </div>

      {open && <AddProjectDialog />}
    </div>
  );
};

export default Sidebar;
