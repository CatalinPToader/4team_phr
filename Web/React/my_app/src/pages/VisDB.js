import React, {Component} from 'react'
import {Table} from 'react-bootstrap'
import '../styling/Table.css'
import SearchBar from "../components/SearchBar"


export class VisDB extends Component {
    constructor(props) {
        super(props)
        this.state = {pacienti:[]}
    }

    refreshList() {
        fetch(process.env.REACT_APP_API+'pacient')
        .then(response=>response.json())
        .then(data => {
            this.setState({pacienti:data});
        });
    }

    componentDidMount() {
        this.refreshList();
    }

    componentDidUpdate() {
        this.refreshList();
    }

    render(){
        const {pacienti} = this.state;

        return(
            <div>
                <SearchBar className="SearchBar"/>
                <Table className='px-2'  center bordered hover size='sm'>
                    <thead>
                        <th>Id</th>
                        <th>Nume</th>
                        <th>Prenume</th>
                        <th>ID_Medic</th>
                    </thead>
                    <tbody>
                        {pacienti.map(pacient=>
                            <tr key={pacient.Id}>
                                <td>{pacient.Id}</td>
                                <td>{pacient.Nume}</td>
                                <td>{pacient.Prenume} </td>
                                <td>{pacient.ID_Medic}</td>
                            </tr>)}
                    </tbody>
                </Table>
            </div>
        )
    }
}