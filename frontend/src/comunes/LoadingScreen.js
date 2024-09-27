import React, { useEffect } from "react";
import "../loading-screen.css";

const LoadingScreen = ({ onLoadingComplete }) => {
  useEffect(() => {
    const timer = setTimeout(() => {
      onLoadingComplete();
    }, 5000);
    return () => clearTimeout(timer);
  }, [onLoadingComplete]);

  return (
    <div className="loading-screen">
      <img
        src={`${process.env.PUBLIC_URL}images/sapresis-logo-animated.gif`}
        alt="Loading..."
      />
    </div>
  );
};

export default LoadingScreen;
