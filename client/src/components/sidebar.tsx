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
        open ? "w-[250px]" : "w-[50px]",
      )}
    >
      <div className="flex w-full justify-end border-b pb-4">
        {open ? (
          <PanelLeftClose
            onClick={() => setOpen(false)}
            className="cursor-pointer"
          />
        ) : (
          <PanelLeftOpen
            onClick={() => setOpen(true)}
            className="cursor-pointer"
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
