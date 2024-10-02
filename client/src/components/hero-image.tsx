"use client";

import Image from "next/image";
import { useTheme } from "next-themes";

const HeroImage = () => {
  const { resolvedTheme } = useTheme();

  const imageSrc =
    resolvedTheme === "dark" ? "/images/hero-dark.png" : "/images/hero.png";

  return (
    <Image
      className="mr-10"
      src={imageSrc}
      alt="Yacht"
      width={350}
      height={350}
    />
  );
};

export default HeroImage;
