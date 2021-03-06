package it.sevenbits.formatters.state.state.factories;

import it.sevenbits.formatters.state.state.*;
import it.sevenbits.formatters.state.state.commands.*;
import it.sevenbits.formatters.state.state.engines.SimpleStateEngine;
import it.sevenbits.formatters.state.state.engines.StateEngine;
import it.sevenbits.formatters.state.tokens.*;
import it.sevenbits.other.ContainerStringBuilder;
//Как выключить в IDEA то что она собирает импорты в * ?
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LexerStateEngineFactory implements StateEngineFactory<Command> {

    /**
     * @return StateMap
     */
    public StateMap getStateMap() {
        Map<Pair<State, String>, State> states = new HashMap<>();

        State defaultState = new State("READING");
        State spacesState = new State("SPACES");
        State stringLiteralState = new State("STRING_LITERAL");
        State preCommentState = new State("PRE_COMMENT");
        State oneLineCommentState = new State("ONE_LINE_COMMENT");

        states.put(new Pair<>(defaultState, ""), defaultState);
        states.put(new Pair<>(defaultState, "\n"), defaultState);
        states.put(new Pair<>(defaultState, "\r"), defaultState);
        states.put(new Pair<>(defaultState, ";"), defaultState);
        states.put(new Pair<>(defaultState, "{"), defaultState);
        states.put(new Pair<>(defaultState, "}"), defaultState);

        states.put(new Pair<>(defaultState, " "), spacesState);

        states.put(new Pair<>(spacesState, " "), spacesState);

        states.put(new Pair<>(defaultState, "\""), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, "\""), defaultState);
        states.put(new Pair<>(stringLiteralState, ""), stringLiteralState);


        states.put(new Pair<>(defaultState, "/"), preCommentState);
        states.put(new Pair<>(preCommentState, "/"), oneLineCommentState);
        states.put(new Pair<>(oneLineCommentState, "\n"), defaultState);
        states.put(new Pair<>(oneLineCommentState, ""), oneLineCommentState);

        states.put(new Pair<>(preCommentState, ""), defaultState);
        states.put(new Pair<>(preCommentState, "\n"), defaultState);
        states.put(new Pair<>(preCommentState, "\r"), defaultState);
        states.put(new Pair<>(preCommentState, ";"), defaultState);
        states.put(new Pair<>(preCommentState, "{"), defaultState);
        states.put(new Pair<>(preCommentState, "}"), defaultState);

        states.put(new Pair<>(spacesState, ""), defaultState);
        states.put(new Pair<>(spacesState, "\n"), defaultState);
        states.put(new Pair<>(spacesState, "\r"), defaultState);
        states.put(new Pair<>(spacesState, ";"), defaultState);
        states.put(new Pair<>(spacesState, "{"), defaultState);
        states.put(new Pair<>(spacesState, "}"), defaultState);

        return new StateMap(states, defaultState);
    }

    @Override
    public StateEngine<Command> getStateEngine() {
        Map<Pair<State, String>, Command> commands = new HashMap<>();

        State defaultState = new State("READING");
        State spacesState = new State("SPACES");
        State stringLiteralState = new State("STRING_LITERAL");
        State preCommentState = new State("PRE_COMMENT");
        State oneLineCommentState = new State("ONE_LINE_COMMENT");


        ContainerStringBuilder cs = new ContainerStringBuilder();
        ContainerStringBuilder forSpaces = new ContainerStringBuilder();

        commands.put(new Pair<>(defaultState, ""), new AppendChar(cs));
        commands.put(new Pair<>(defaultState, "\n"), new AddTokenWith(new NewLine(), cs, "Word"));
        commands.put(new Pair<>(defaultState, "\r"), new AddTokenWith(new NewLine(), cs, "Word"));
        commands.put(new Pair<>(defaultState, ";"), new AddTokenWith(new Semicolon(), cs, "Word"));
        commands.put(new Pair<>(defaultState, "{"), new AddTokenWith(new OpenBrace(), cs, "Word"));
        commands.put(new Pair<>(defaultState, "}"), new AddTokenWith(new CloseBrace(), cs, "Word"));


        commands.put(new Pair<>(defaultState, " "), new AppendChar(forSpaces));

        commands.put(new Pair<>(spacesState, " "),  new AppendChar(forSpaces));

        commands.put(new Pair<>(defaultState, "\""), new AppendCharPrintAndReset(cs, forSpaces));
        commands.put(new Pair<>(stringLiteralState, "\""), new AppendCharPrintAndReset(cs, forSpaces));
        commands.put(new Pair<>(stringLiteralState, ""), new AppendChar(cs));

        commands.put(new Pair<>(spacesState, ""), new AppendCharPrintAndReset(cs, forSpaces));
        commands.put(new Pair<>(spacesState, "\n"), new AddTokenWithSpaces(new NewLine(), cs, "Word", forSpaces));
        commands.put(new Pair<>(spacesState, "\r"), new AddTokenWithSpaces(new NewLine(), cs, "Word", forSpaces));
        commands.put(new Pair<>(spacesState, ";"), new AddTokenWithSpaces(new Semicolon(), cs, "Word", forSpaces));
        commands.put(new Pair<>(spacesState, "{"), new AddTokenWithSpaces(new OpenBrace(), cs, "Word", forSpaces));
        commands.put(new Pair<>(spacesState, "}"), new AddTokenWithSpaces(new CloseBrace(), cs, "Word", forSpaces));


        commands.put(new Pair<>(defaultState, "/"), new AppendCharPrintAndReset(cs, forSpaces));
        commands.put(new Pair<>(preCommentState, "/"), new AppendChar(cs));
        commands.put(new Pair<>(oneLineCommentState, "\n"), new AppendCharWithToken(cs, new UniversalToken("Comment", "")));
        commands.put(new Pair<>(oneLineCommentState, ""), new AppendChar(cs));

        commands.put(new Pair<>(preCommentState, ""),  new AppendChar(cs));
        commands.put(new Pair<>(preCommentState, "\n"), new AddTokenWith(new NewLine(), cs, "Word"));
        commands.put(new Pair<>(preCommentState, "\r"), new AddTokenWith(new NewLine(), cs, "Word"));
        commands.put(new Pair<>(preCommentState, ";"), new AddTokenWith(new Semicolon(), cs, "Word"));
        commands.put(new Pair<>(preCommentState, "{"), new AddTokenWith(new OpenBrace(), cs, "Word"));
        commands.put(new Pair<>(preCommentState, "}"), new AddTokenWith(new CloseBrace(), cs, "Word"));

        return new SimpleStateEngine(getStateMap(), commands);
    }

}
