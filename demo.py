"""
@author     : Rajan Khullar
@created    : 01/08/16
@updated    : 01/08/16
"""

from simulator import Simulator

sim = Simulator(2)
sim.load('processes.csv')
sim.schedule()

for event in sim.events:
    print(event)
