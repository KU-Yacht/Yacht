const Page = ({ params }: { params: { projectId: string } }) => {
  return (
    <div className="p-10">
      <h1 className="text-2xl font-bold">Project {params.projectId}</h1>
    </div>
  );
};

export default Page;
