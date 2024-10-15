import ProjectsList from "@/components/projects-list";
import Sidebar from "@/components/sidebar";
import { ReactNode } from "react";

const Layout = ({ children }: { children: ReactNode }) => {
  return (
    <div className="flex flex-1 flex-col md:flex-row">
      <Sidebar>
        <ProjectsList />
      </Sidebar>
      {children}
    </div>
  );
};

export default Layout;
