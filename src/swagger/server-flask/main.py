#!/usr/bin/env python3

import controllers.users as users
import signal
import sys
import runpy

def on_ctrlc(signal, frame):
    users.on_exit()
    sys.exit(0)

    
signal.signal(signal.SIGINT, on_ctrlc)

users.on_start()

runpy.run_module('swagger_server', run_name='__main__', alter_sys=True)

# We shall not get here unless an error occured.
users.on_exit()
