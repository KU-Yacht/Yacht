import Image from "next/image";
import { Button } from "./ui/button";
import { ModeToggle } from "./ui/mode-toggle";
import Link from "next/link";

const Header = () => {
  return (
    <header className="flex items-center justify-between gap-10 border-b p-4">
      <Link href="/" className="flex items-center gap-4">
        <Image src="/images/logo.svg" alt="Yacht" width={40} height={40} />
        <h1 className="font-geistSans text-2xl font-semibold">Yacht</h1>
      </Link>
      <div className="flex items-center gap-4">
        <ModeToggle />
        <Button asChild>
          <Link href="/login">Login</Link>
        </Button>
      </div>
    </header>
  );
};

export default Header;
