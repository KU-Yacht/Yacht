import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Plus } from "lucide-react";

const AddProjectDialog = () => {
  return (
    <Dialog>
      <DialogTrigger asChild>
        <div className="flex w-full cursor-pointer items-center justify-center rounded-lg border p-2 hover:bg-gray-200 dark:hover:bg-gray-800">
          <Plus className="cursor-pointer" />
        </div>
      </DialogTrigger>
      <DialogContent className="w-[350px] rounded-xl md:w-[450px]">
        <DialogHeader>
          <DialogTitle>Add new Project</DialogTitle>
          <DialogDescription>
            Create a new project to start building your application.
          </DialogDescription>
        </DialogHeader>
        <div className="grid gap-4 py-4">
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="name" className="text-center">
              Name
            </Label>
            <Input id="name" className="col-span-3" />
          </div>
        </div>
        <DialogFooter>
          <Button type="submit" className="flex items-center gap-4">
            <Plus size={16} />
            Add
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};

export default AddProjectDialog;
