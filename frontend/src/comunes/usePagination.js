import { useMemo } from "react";

export const DOTS = "...";

const range = (start, end) => {
  let length = end - start + 1;
  return Array.from({ length }, (_, idx) => idx + start);
};

export const usePagination = ({
  totalCount,
  pageSize,
  siblingCount = 1,
  currentPage,
}) => {
  const paginationRange = useMemo(() => {
    const totalPageCount = Math.ceil(totalCount / pageSize);

    // La cuenta de paginación está condicionada a que haya al menos 5 páginas
    const totalPageNumbers = siblingCount + 5;

    /*
        Caso 1: 
        Cuando el número total de páginas es menor que el número total de páginas que se mostrarán en la paginación
        , simplemente devuelva un rango de 1 a totalPageCount
      */
    if (totalPageNumbers >= totalPageCount) {
      return range(1, totalPageCount);
    }

    /*
          Se calcula el rango de páginas que se mostrarán en la paginación
          Se esablce el índice de la página contigua a la derecha e izquierda del índice de la página actual
      */
    const leftSiblingIndex = Math.max(currentPage - siblingCount, 1);
    const rightSiblingIndex = Math.min(
      currentPage + siblingCount,
      totalPageCount
    );

    /*
        No se muestran puntos suspensivos a la izquierda si el primer índice de la página es 1
        Tampoco se muestran puntos suspensivos a la derecha si el último índice de la página es igual al total de páginas
      */
    const shouldShowLeftDots = leftSiblingIndex > 2;
    const shouldShowRightDots = rightSiblingIndex < totalPageCount - 2;

    const firstPageIndex = 1;
    const lastPageIndex = totalPageCount;

    /*
          Caso 2: 
          No se muestran puntos suspensivos a la izquierda, pero se muestran a la derecha
      */
    if (!shouldShowLeftDots && shouldShowRightDots) {
      let leftItemCount = 3 + 2 * siblingCount;
      let leftRange = range(1, leftItemCount);

      return [...leftRange, DOTS, totalPageCount];
    }

    /*
          Caso 3: 
          No se muestran puntos suspensivos a la derecha, pero se muestran a la izquierda
      */
    if (shouldShowLeftDots && !shouldShowRightDots) {
      let rightItemCount = 3 + 2 * siblingCount;
      let rightRange = range(
        totalPageCount - rightItemCount + 1,
        totalPageCount
      );
      return [firstPageIndex, DOTS, ...rightRange];
    }

    /*
          Caso 4: 
          Se muestran puntos suspensivos a la izquierda y a la derecha
      */
    if (shouldShowLeftDots && shouldShowRightDots) {
      let middleRange = range(leftSiblingIndex, rightSiblingIndex);
      return [firstPageIndex, DOTS, ...middleRange, DOTS, lastPageIndex];
    }
  }, [totalCount, pageSize, siblingCount, currentPage]);

  return paginationRange;
};
