import React, {Component} from 'react'
import {Table} from 'react-bootstrap'


export class VisDB extends Component {
    constructor(props) {
        super(props)
        this.state = {useri:[]}
    }

    refreshList() {
        fetch(process.env.REACT_APP_API+'user')
        .then(response=>response.json())
        .then(data => {
            this.setState({useri:data});
        });
    }

    componentDidMount() {
        this.refreshList();
    }

    componentDidUpdate() {
        this.refreshList();
    }

    render(){
        const {useri} = this.state;

        return(
            <div>
                <Table className='mt-4' striped bordered hover size='sm'>
                    <thead>
                        <th>Mail</th>
                        <th>Password</th>
                        <th>Status</th>
                    </thead>
                    <tbody>
                        {useri.map(user=>
                            <tr key={user.Email}>
                                <td>{user.Email}</td>
                                <td>{user.Password} </td>
                                <td>Edit / Delete</td>
                            </tr>)}
                    </tbody>
                </Table>
            </div>
        )
    }
}