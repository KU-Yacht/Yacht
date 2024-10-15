import Project from "./project";

const dummyProjects = [
  {
    id: 1,
    name: "Project 1",
    applications: [
      {
        id: 1,
        name: "Application 1",
        description: "Application 1 description",
      },
      {
        id: 2,
        name: "Application 2",
        description: "Application 2 description",
      },
    ],
  },
  {
    id: 2,
    name: "Project 2",
    applications: [
      {
        id: 3,
        name: "Application 3",
        description: "Application 3 description",
      },
      {
        id: 4,
        name: "Application 4",
        description: "Application 4 description",
      },
    ],
  },
  {
    id: 3,
    name: "Project 3",
    applications: [
      {
        id: 5,
        name: "Application 5",
        description: "Application 5 description",
      },
      {
        id: 6,
        name: "Application 6",
        description: "Application 6 description",
      },
    ],
  },
];

const ProjectsList = () => {
  return (
    <div className="flex w-full flex-col gap-10 p-8">
      {dummyProjects.map((project) => (
        <Project key={project.id} data={project} />
      ))}
    </div>
  );
};

export default ProjectsList;
