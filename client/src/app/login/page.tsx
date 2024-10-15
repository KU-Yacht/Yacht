import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import Image from "next/image";
import Link from "next/link";

const Login = () => {
  return (
    <div className="flex flex-1 flex-col items-center justify-center gap-8 p-10">
      <Image src="/images/logo.svg" alt="Yacht" width={75} height={75} />
      <h1 className="text-center text-2xl font-semibold">Login to Yacht</h1>
      <form className="flex min-w-[400px] flex-col justify-center gap-6 rounded-lg border p-8">
        <div className="flex flex-col gap-2">
          <Label
            htmlFor="email"
            className="text-lg font-semibold text-gray-800 dark:text-gray-100"
          >
            Email address
          </Label>
          <Input type="email" placeholder="Email address" required id="email" />
        </div>
        <div className="flex flex-col gap-2">
          <Label
            htmlFor="password"
            className="text-lg font-semibold text-gray-800 dark:text-gray-100"
          >
            Password
          </Label>
          <Input type="password" placeholder="Password" required id="email" />
        </div>
        <div className="flex flex-col gap-4">
          <Button type="submit">Login</Button>
          <Button variant="secondary" asChild>
            <Link href="/register">Register</Link>
          </Button>
        </div>
      </form>
    </div>
  );
};

export default Login;
