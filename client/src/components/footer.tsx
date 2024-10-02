import Link from "next/link";

const Footer = () => {
  return (
    <footer className="p-4 w-full flex flex-col items-end text-[10px]">
      <Link href="https://github.com/KU-Yacht">
        <p>by TEAM Yacht</p>
      </Link>
    </footer>
  );
};

export default Footer;
