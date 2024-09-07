import React from "react";
import classnames from "classnames";
import { usePagination, DOTS } from "./usePagination";
import "../pagination.css";

/**
 * Componente que renderiza la paginación de las listas
 * @param {Object} props Las propiedades del componente
 * @param {Function} props.onPageChange Función que se ejecuta cuando se cambia de página
 * @param {number} props.totalCount El número total de elementos en la lista
 * @param {number} props.siblingCount El número de elementos a mostrar en la paginación
 * @param {number} props.currentPage La página actual
 * @param {number} props.pageSize El número de elementos por página
 * @param {string} props.className La clase CSS del componente
 * @returns El componente de paginación
 * @requires classnames Para combinar clases CSS, usePagination, DOTS
 * @version 1.0
 */

const Pagination = (props) => {
  const {
    onPageChange,
    totalCount,
    siblingCount = 1,
    currentPage,
    pageSize,
    className,
  } = props;

  const paginationRange = usePagination({
    currentPage,
    totalCount,
    siblingCount,
    pageSize,
  });

  // Si hay menos de 2 elementos en el rango de paginación, no se renderiza
  if (currentPage === 0 || paginationRange.length < 2) {
    return null;
  }

  const onNext = () => {
    onPageChange(currentPage + 1);
  };

  const onPrevious = () => {
    onPageChange(currentPage - 1);
  };

  let lastPage = paginationRange[paginationRange.length - 1];
  return (
    <ul
      className={classnames("pagination-container", {
        [className]: className,
      })}>
      {/* Flecha de navegación izquierda */}
      <li
        className={classnames("pagination-item", {
          disabled: currentPage === 1,
        })}
        onClick={onPrevious}>
        <div className="arrow left" />
      </li>
      {paginationRange.map((pageNumber) => {
        if (pageNumber === DOTS) {
          return <li className="pagination-item dots">&#8230;</li>;
        }

        return (
          <li
            className={classnames("pagination-item", {
              selected: pageNumber === currentPage,
            })}
            onClick={() => onPageChange(pageNumber)}>
            {pageNumber}
          </li>
        );
      })}
      {/*  Flecha de navegación derecha */}
      <li
        className={classnames("pagination-item", {
          disabled: currentPage === lastPage,
        })}
        onClick={onNext}>
        <div className="arrow right" />
      </li>
    </ul>
  );
};

export default Pagination;
