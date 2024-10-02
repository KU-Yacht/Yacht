import Head from "next/head";

interface SEOProps {
  title: string;
}

const SEO = ({ title }: SEOProps) => {
  return (
    <Head>
      <title>{title}</title>
      <meta
        name="description"
        content="Kubernetes Build, Deployment, and Operations Automation Platform"
      />
      <meta property="og:type" content="website" />
      <meta property="og:title" content={title} />
      <meta
        property="og:description"
        content="Kubernetes Build, Deployment, and Operations Automation Platform"
      />
      <meta property="og:url" content="https://yacht-liard.vercel.app/" />
      <meta property="og:locale" content="en_US" />
      <meta name="og:image" content="/images/background-logo.png" />
    </Head>
  );
};

export default SEO;
