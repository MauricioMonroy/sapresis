import React, { useEffect } from "react";
import PropTypes from "prop-types";
import "../loading-screen.css";

/**
 * @param {*} param0
 * @returns
 */
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
LoadingScreen.propTypes = {
  onLoadingComplete: PropTypes.func.isRequired,
};

export default LoadingScreen;
