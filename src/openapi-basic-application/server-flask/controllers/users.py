import connexion

from http import HTTPStatus
from openapi_server.models.user import User


# Permissive CORS header to be added to responses.
# Standard solution would use the flask CORS package
# but that would require modifying more generated files.

CORS = { 'Access-Control-Allow-Origin' : '*' }


user_last_id = 1
user_data = {}


def read_users ():
    global user_data
    return list (user_data.values ()), HTTPStatus.OK.value, CORS

def create_user (body):
    global user_last_id
    global user_data
    if connexion.request.is_json:
        body = User.from_dict (connexion.request.get_json ())
    body.id = user_last_id
    user_last_id += 1
    user_data [body.id] = body
    return None, HTTPStatus.CREATED.value, CORS

def delete_user (user_id):
    global user_data
    try:
        del user_data [user_id]
        return None, HTTPStatus.NO_CONTENT.value, CORS
    except KeyError:
        return None, HTTPStatus.NOT_FOUND.value, CORS

def read_user (user_id):
    global user_data
    try:
        return user_data [user_id], HTTPStatus.OK.value, CORS
    except KeyError:
        return None, HTTPStatus.NOT_FOUND.value, CORS

def update_user (body, user_id):
    global user_data
    if connexion.request.is_json:
        body = User.from_dict (connexion.request.get_json ())
    body.id = user_id
    if body.id in user_data:
        user_data [body.id] = body
        return None, HTTPStatus.RESET_CONTENT.value, CORS
    else:
        return None, HTTPStatus.NOT_FOUND.value, CORS
