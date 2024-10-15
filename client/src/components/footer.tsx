import Link from "next/link";

const Footer = () => {
  return (
    <footer className="flex w-full flex-col items-end p-4 text-[10px]">
      <Link href="https://github.com/KU-Yacht">
        <p>by TEAM Yacht</p>
      </Link>
    </footer>
  );
};

export default Footer;
