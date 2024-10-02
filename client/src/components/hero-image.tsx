"use client";

import Image from "next/image";
import { useTheme } from "next-themes";

const HeroImage = () => {
  const { theme } = useTheme();
  return (
    <Image
      className="mr-10"
      src={theme === "dark" ? "/images/hero-dark.png" : "/images/hero.png"}
      alt="Yacht"
      width={350}
      height={350}
    />
  );
};

export default HeroImage;
