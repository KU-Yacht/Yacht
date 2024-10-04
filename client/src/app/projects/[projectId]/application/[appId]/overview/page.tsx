import {
  CircleCheck,
  GitBranch,
  GitCommit,
  SquareArrowOutUpRight,
} from "lucide-react";
import Image from "next/image";
import Link from "next/link";

const Page = () => {
  return (
    <div className="mt-10 flex flex-col items-center gap-4 md:flex-row">
      <Image
        src="/images/hero.png"
        width={500}
        height={300}
        alt="preview"
        className="h-[300px] w-[500px] rounded-xl border object-cover"
      />

      <div className="flex flex-col gap-4 p-10">
        <div className="flex items-center gap-10 md:gap-20">
          <div className="flex flex-col gap-2">
            <h1 className="text-xl text-gray-500">Domain</h1>
            <Link
              href="https://google.com"
              className="flex items-center gap-2 hover:underline"
            >
              https://google.com
              <SquareArrowOutUpRight />
            </Link>
          </div>

          <div className="flex flex-col gap-2">
            <h1 className="text-xl text-gray-500">Status</h1>
            <span className="flex items-center gap-2 text-green-600">
              <CircleCheck />
              Active
            </span>
          </div>
        </div>

        <div className="flex flex-col gap-2">
          <h1 className="text-xl text-gray-500">Created</h1>
          <span className="flex items-center gap-2">
            55m ago by{" "}
            <Link href="https://github.com/MinboyKim" className="underline">
              Minboy
            </Link>
          </span>
        </div>

        <div className="flex flex-col gap-2">
          <h1 className="text-xl text-gray-500">Sources</h1>
          <span className="flex items-center gap-2">
            <GitBranch />
            main
          </span>
          <span className="flex items-center gap-2">
            <GitCommit />
            d6aad6
          </span>
        </div>
      </div>
    </div>
  );
};

export default Page;
