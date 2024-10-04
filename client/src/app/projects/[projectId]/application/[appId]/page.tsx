import { redirect } from "next/navigation";

const Page = ({ params }: { params: { projectId: string; appId: string } }) => {
  redirect(
    `/projects/${params.projectId}/application/${params.appId}/overview`,
  );
};

export default Page;
