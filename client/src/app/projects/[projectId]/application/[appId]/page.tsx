const Page = ({ params }: { params: { appId: string } }) => {
  return (
    <div className="p-10">
      <h1 className="text-2xl font-bold">Application {params.appId}</h1>
    </div>
  );
};

export default Page;
