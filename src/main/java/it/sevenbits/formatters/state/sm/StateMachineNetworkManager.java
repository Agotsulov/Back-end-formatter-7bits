package it.sevenbits.formatters.state.sm;

/*
import it.sevenbits.courses.sm.log.MessageFormatter;
import it.sevenbits.courses.sm.manager.INetworkManager;
import it.sevenbits.courses.sm.manager.NetworkManagerException;
import it.sevenbits.courses.sm.manager.sm.cmd.*;
import it.sevenbits.courses.sm.network.INetwork;
import it.sevenbits.courses.sm.network.NetworkPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class StateMachineNetworkManager implements INetworkManager {

    private boolean isInterrupted = false;
    private final long TIMEOUT = 500;
    private final StateTransition stateTransition;

    private Map<Pair<State, String>, INetworkManagerCommand> cmds;

    private Logger logger = LoggerFactory.getLogger(StateMachineNetworkManager.class);

    public StateMachineNetworkManager() {
        this.stateTransition = new StateTransition();
        cmds = new HashMap<>();
    }

    private void createCmds(StringBuilder buffer, NetworkContainer container) {
        cmds.clear();

        State defaultState = new State("IGNORE");
        State listenState = new State("LISTEN");
        State stubSuspicion = new State("TRASH_SUSPICION");

        cmds.put(new Pair<>(defaultState, "MESSAGE_START"), new PutInCommand(container, buffer));
        cmds.put(new Pair<>(defaultState, "TRASH"), new IgnoreCommand());
        cmds.put(new Pair<>(defaultState, "MESSAGE"), new IgnoreCommand());
        cmds.put(new Pair<>(defaultState, "MESSAGE_FINISH"), new IgnoreCommand());

        cmds.put(new Pair<>(listenState, "MESSAGE"), new PutInCommand(container, buffer));
        cmds.put(new Pair<>(listenState, "MESSAGE_START"), new IgnoreCommand());
        cmds.put(new Pair<>(listenState, "TRASH"), new IgnoreCommand());
        cmds.put(new Pair<>(listenState, "MESSAGE_FINISH"), new WriteAndClearCommand(buffer));

        cmds.put(new Pair<>(stubSuspicion, "MESSAGE"), new PutInCommand(container, buffer));
        cmds.put(new Pair<>(stubSuspicion, "MESSAGE_FINISH"), new WriteAndClearCommand(buffer));
        cmds.put(new Pair<>(stubSuspicion, "MESSAGE_START"), new IgnoreCommand());
        cmds.put(new Pair<>(stubSuspicion, "TRASH"), new ClearCommand(buffer));
    }

    @Override
    public void listen(INetwork network) throws NetworkManagerException {
        State currentState = stateTransition.getStartState();

        MessageFormatter messageFormatter = new MessageFormatter();

        StringBuilder buffer = new StringBuilder("");

        NetworkContainer container = new NetworkContainer();

        createCmds(buffer, container);
        try {
            while(!isInterrupted){
                while(network.hasPackage()){
                    NetworkPackage p = network.getPackage();
                    container.setP(p);
                    logger.info(String.format(messageFormatter.getStringFormatByType(p.getType()),
                            currentState.toString()));
                    cmds.get(new Pair<>(currentState, p.getType())).execute();
                    currentState = stateTransition.nextState(currentState, p);
                }

                Thread.sleep(TIMEOUT);
            }
        } catch (InterruptedException e){
            logger.error("Network manager was interrupted unexpectedly");
            throw new NetworkManagerException("Network manager was interrupted unexpectedly", e);
        }
    }

    @Override
    public void stop() {
        logger.debug("isInterrupted = " + isInterrupted);
        isInterrupted = true;
    }
}
*/