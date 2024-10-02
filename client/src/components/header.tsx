import Image from "next/image";
import { Button } from "./ui/button";
import { ModeToggle } from "./ui/mode-toggle";
import Link from "next/link";

const Header = () => {
  return (
    <header className="p-4 flex gap-10 justify-between items-center border-b">
      <div className="flex gap-4 items-center">
        <Link href="/">
          <Image src="/images/logo.svg" alt="Yacht" width={60} height={60} />
        </Link>
        <h1 className="font-geistSans text-3xl font-semibold">Yacht</h1>
      </div>
      <div className="flex gap-4 items-center">
        <ModeToggle />
        <Button asChild>
          <Link href="/login">Login</Link>
        </Button>
      </div>
    </header>
  );
};

export default Header;
