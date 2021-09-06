import React from "react";
import { useTable, usePagination, useSortBy } from "react-table";

const Pagedtable = ({ columns, data, children, className, onRowClick, showPageSize, defaultPageSize }) => {
  const {
    page,
    getTableBodyProps,
    headerGroups,
    prepareRow,
    canPreviousPage,
    canNextPage,
    pageOptions,
    pageCount,
    gotoPage,
    nextPage,
    previousPage,
    setPageSize,
    state: { pageIndex, pageSize },
  } = useTable(
    {
      columns,
      data,
	initialState: { pageIndex: 0, pageSize: defaultPageSize ? defaultPageSize : 15 },
    },
    useSortBy,
    usePagination
  );

  const buttonClass = "btn btn-secondary me-2";
  const spanAlign = "d-flex align-items-center";
  const tableClass =
    "table table-hover width-80 mx-auto my-5 vertical-middle " + className;

  return (
    <React.Fragment>
      <table className={tableClass} {...getTableBodyProps()}>
        <thead>
          {headerGroups.map((headerGroup) => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map((column) => (
                <th {...column.getHeaderProps(column.getSortByToggleProps)}>
                  {column.render("Header")}
                  {column.canSort ? (
                    column.isSorted ? (
                     <img
                        src={
                          column.isSortedDesc
                            ? process.env.PUBLIC_URL + "/angle-down-solid.svg"
                            : process.env.PUBLIC_URL + "/angle-up-solid.svg"
                        }
                        className="mx-2"
                        style={{ height: "20px" }}
                        alt=""
                      />
                    ) : (
                      <img
                        src={process.env.PUBLIC_URL + "/unsorted.svg"}
                        alt="Unsorted"
                        style={{ height: "20px" }}
                        className="mx-2"
                      />
                    )
                  ) : (
                    ""
                  )}
                </th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {page.map((row, i) => {
            prepareRow(row);
            return (
              <tr
                {...row.getRowProps()}
                onClick={() => (onRowClick ? onRowClick(row.original) : "")}
              >
                {row.cells.map((cell) => {
                  return (
                    <td {...cell.getCellProps()}> {cell.render("Cell")} </td>
                  );
                })}
              </tr>
            );
          })}
        </tbody>
      </table>

      {children}

      {/* Pagination */}

      <div className="pagination justify-content-center">
        <button
          className={buttonClass}
          onClick={() => gotoPage(0)}
          disabled={!canPreviousPage}
        >
          {"<<"}
        </button>{" "}
        <button
          className={buttonClass}
          onClick={() => previousPage()}
          disabled={!canPreviousPage}
        >
          {"<"}
        </button>{" "}
        <button
          className={buttonClass}
          onClick={() => nextPage()}
          disabled={!canNextPage}
        >
          {">"}
        </button>{" "}
        <button
          className={buttonClass}
          onClick={() => gotoPage(pageCount - 1)}
          disabled={!canNextPage}
        >
          {">>"}
        </button>{" "}
        <span className={spanAlign}>
          Page
          <strong className="mx-1">
            {pageIndex + 1} of {pageOptions.length}
          </strong>{" "}
        </span>
        <span className={spanAlign}>
          | Go to page:{" "}
          <input
            type="number"
            defaultValue={pageIndex + 1}
            onChange={(e) => {
              const page = e.target.value ? Number(e.target.value) - 1 : 0;
              gotoPage(page);
            }}
            style={{ width: "100px" }}
          />
        </span>{" "}
	  {showPageSize ? 
           <select
               value={pageSize}
               onChange={(e) => {
		   setPageSize(Number(e.target.value));
               }}
           >
               {[3, 7, 15].map((pageSize) => (
		   <option key={pageSize} value={pageSize}>
		       Show {pageSize}
		   </option>
               ))}
           </select>
	   :""
	  }
    </div>
    </React.Fragment>
  );
};

export default Pagedtable;
