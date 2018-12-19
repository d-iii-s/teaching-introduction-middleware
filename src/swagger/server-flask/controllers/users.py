import connexion
import six

from swagger_server.models.user import User
from swagger_server.models.user_base import UserBase
from swagger_server import util
import sys
import yaml

DATA = {
    'users': [],
    'users.seq': 1,
}

def on_exit():
    global DATA
    
    with open('data.yaml', 'w') as f:
        tmp = {
            'data': {
                'users': [],
                'users.seq': DATA['users.seq'],
            }
        }
        
        for u in DATA['users']:
            tmp['data']['users'].append(u.to_dict())
        
        yaml.dump(tmp, f, default_flow_style=False)
        

def load_data_from_file(filename):
    with open(filename, 'r') as f:
        try:
            data = yaml.load(f)
        except yaml.YAMLError as exc:
            print(exc)
            sys.exit(1)
    
    global DATA
    
    DATA['users'] = []
    for u in data['data']['users']:
        try:
            u = User.from_dict(u)
            DATA['users'].append(u)
        except Exception as e:
            print(e)
    
    if 'users.seq' in data['data']:
        DATA['users.seq'] = data['data']['users.seq']
    else:
        for u in DATA['users']:
            if u.id >= DATA['users.seq']:
                DATA['users.seq'] = u.id + 1
                

def on_start():
    try:
        load_data_from_file('data.yaml')
    except FileNotFoundError as exc:
        load_data_from_file('data.init.yaml')


def create_user(body):
    """Creates a new user.

     # noqa: E501

    :param body: User to be added.
    :type body: dict | bytes

    :rtype: None
    """
    
    if connexion.request.is_json:
        body = User.from_dict(connexion.request.get_json())
    
    global DATA
    
    body.id = DATA['users.seq']
    DATA['users.seq'] = DATA['users.seq'] + 1
    DATA['users'].append(body)


def delete_user(id):  # noqa: E501
    """Delete a user.

     # noqa: E501

    :param id: ID of the user.
    :type id: int

    :rtype: None
    """
    
    global DATA
    
    for idx, u in enumerate(DATA['users']):
        if u.id == id:
            del DATA['users'][idx]
            return
    
    return None, 404


def read_user(id):  # noqa: E501
    """Basic information about a user.

     # noqa: E501

    :param id: ID of the user.
    :type id: int

    :rtype: User
    """
    
    global DATA
    
    for u in DATA['users']:
        if u.id == id:
            return u
    
    return None, 404


def read_users():  # noqa: E501
    """Lists all users.

     # noqa: E501


    :rtype: List[UserBase]
    """
    global DATA
    
    result = []
    for u in DATA['users']:
        result.append(UserBase.from_dict(u.to_dict()))
    
    return result


def update_user(id, body):  # noqa: E501
    """Update existing user.

     # noqa: E501

    :param id: ID of the user.
    :type id: int
    :param body: Updated data.
    :type body: dict | bytes

    :rtype: None
    """
    
    if connexion.request.is_json:
        body = User.from_dict(connexion.request.get_json())
    
    global DATA
    
    for idx, u in enumerate(DATA['users']):
        if u.id == id:
            DATA['users'][idx] = body
            return
    
    return None, 404

