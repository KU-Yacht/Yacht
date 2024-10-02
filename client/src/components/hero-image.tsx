"use client";

import Image from "next/image";
import { useTheme } from "next-themes";
import { useEffect, useState } from "react";

const HeroImage = () => {
  const { resolvedTheme } = useTheme();
  const [mounted, setMounted] = useState(false);

  const imageSrc =
    resolvedTheme === "dark" ? "/images/hero-dark.png" : "/images/hero.png";

  useEffect(() => setMounted(true), []);

  if (!mounted) return null;

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
