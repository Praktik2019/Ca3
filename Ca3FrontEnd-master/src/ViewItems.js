import React, { Component } from 'react';
import '../node_modules/react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import filterFactory, { textFilter } from 'react-bootstrap-table2-filter';
import facade from './apiFacade';
import { Link } from "react-router-dom";
import ViewCart from './ViewCart';

const columns = [{
    dataField: 'name',
    text: 'Name',
    sort: true,
    filter: textFilter(),
    formatter: viewDetails
}, {
    dataField: 'type',
    text: 'Type',
    sort: true,
    filter: textFilter()
}, {
    dataField: 'flavour',
    text: 'Flavour',
    sort: true,
    filter: textFilter()
}, {
    dataField: 'stock',
    text: 'Stock',
    sort: true,
    filter: textFilter()
}];

function viewDetails(cell, row) {
    return (<Link to={"/candy/" + row.id}>{row.name}</Link>);
}
/* const rowEvents = {
    onClick: (e, row) => {
        console.log("row.id: " + row.id);
        return <Link className="nav-link" to="/candy">Pagination</Link>;
    }
}*/
class AppClientPagination extends Component {
    state = { candy: [] }
    async componentDidMount() {
        const candy = await facade.fetchTableData();
        this.setState({ candy });
    }
    
    render() {
        return <div>
            <div className="container" id="white">
                <h2>View Candy</h2>
                <BootstrapTable bootstrap4={true} keyField='id' data={this.state.candy} columns={columns} filter={filterFactory()} pagination={paginationFactory()} />
            </div>
        </div>

    }
}

export default AppClientPagination;