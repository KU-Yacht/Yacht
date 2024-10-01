import { Button } from "@/components/ui/button";
import { ModeToggle } from "@/components/ui/mode-toggle";

export default function Home() {
  return (
    <div>
      <h1 className="text-4xl font-bold">Hello, world!</h1>
      <Button>Click</Button>
      <ModeToggle />
    </div>
  );
}
