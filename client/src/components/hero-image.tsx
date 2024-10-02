"use client";

import Image from "next/image";
import { useTheme } from "next-themes";
import { useEffect, useState } from "react";

const HeroImage = () => {
  const { theme } = useTheme();
  const [mounted, setMounted] = useState(false);

  // Ensure theme is loaded before rendering to prevent hydration mismatch
  useEffect(() => {
    setMounted(true);
  }, []);

  if (!mounted) {
    return null; // Prevent rendering on the server
  }

  const imageSrc =
    theme === "dark" ? "/images/hero-dark.png" : "/images/hero.png";

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
