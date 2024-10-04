import { Button } from "@/components/ui/button";
import { CheckCircle, Ellipsis, LayoutGrid, Plus } from "lucide-react";
import Link from "next/link";

const dummyProjects = {
  application: [
    {
      id: 1,
      name: "Application 1",
      description: "Application 1 description",
    },
    {
      id: 2,
      name: "Application 2",
      description: "Application 2 description",
    },
  ],
};

const Page = ({ params }: { params: { projectId: string } }) => {
  return (
    <div className="flex h-full w-full flex-col gap-10 p-10">
      <div className="flex items-center justify-between">
        <h1 className="text-3xl font-bold md:text-4xl">
          Project {params.projectId}
        </h1>
        <Button
          asChild
          className="flex items-center gap-4 text-xs md:px-4 md:py-6 md:text-lg"
        >
          <Link href={`/projects/${params.projectId}/create`}>
            <Plus size={24} className="hidden md:block" />
            Create Application
          </Link>
        </Button>
      </div>

      <div className="flex flex-col gap-8">
        {dummyProjects.application.map((application) => (
          <Link
            key={application.id}
            href={`/projects/${params.projectId}/application/${application.id}`}
          >
            <div className="flex w-full cursor-pointer flex-col gap-4 rounded-xl border p-8 hover:bg-gray-300 dark:hover:bg-gray-700">
              <div className="flex items-center gap-4">
                <LayoutGrid size={20} />
                <h2 className="text-2xl font-bold">{application.name}</h2>
              </div>

              <p className="text-gray-500">{application.description}</p>

              <div className="flex flex-col items-center justify-between gap-10 md:flex-row">
                <div className="flex items-center gap-4 rounded-xl border p-4 text-green-600">
                  <CheckCircle size={20} />
                  <h3 className="text-xl font-semibold">Prepare</h3>
                </div>

                <Ellipsis
                  size={30}
                  className="rotate-90 transform md:rotate-0"
                />

                <div className="flex items-center gap-4 rounded-xl border p-4">
                  <CheckCircle size={20} />
                  <h3 className="text-xl font-semibold">Build</h3>
                </div>

                <Ellipsis
                  size={30}
                  className="rotate-90 transform md:rotate-0"
                />

                <div className="flex items-center gap-4 rounded-xl border p-4">
                  <CheckCircle size={20} />
                  <h3 className="text-xl font-semibold">Deploy</h3>
                </div>

                <Ellipsis
                  size={30}
                  className="rotate-90 transform md:rotate-0"
                />

                <div className="flex items-center gap-4 rounded-xl border p-4">
                  <CheckCircle size={20} />
                  <h3 className="text-xl font-semibold">Done</h3>
                </div>
              </div>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default Page;
