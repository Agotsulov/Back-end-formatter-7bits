package it.sevenbits.formatters.state;

import it.sevenbits.core.Formatter;
import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.state.handlers.HandlerException;
import it.sevenbits.formatters.state.lexers.Lexer;
import it.sevenbits.formatters.state.lexers.factories.LexerFactory;
import it.sevenbits.formatters.state.lexers.factories.LexerFactoryException;
import it.sevenbits.formatters.state.state.factories.StateEngineFactory;
import it.sevenbits.formatters.state.state.factories.StateEngineFactoryException;
import it.sevenbits.formatters.state.tokens.Token;
import it.sevenbits.formatters.state.handlers.Handler;
import it.sevenbits.formatters.state.state.engines.StateEngine;
import it.sevenbits.io.Reader;
import it.sevenbits.io.Writer;
import it.sevenbits.io.WriterException;
import it.sevenbits.other.IteratorException;

//TODO: Добавить обработку многострочных комментариев
//TODO: Вынести репозитории переходов и команд (конфигурацию автомата) в файл с настройками


//Вот была бы программа которая сама исправляет ощибки checkstyle по растановики пробелов и т.п...
//ААААааа мы ее и писали... Надеюсь ее не надо пользоваться потом.
/**
 *
 */
public class StateLexerFormatter implements Formatter {

    private LexerFactory lexerFactory;
    private StateEngineFactory<Handler> stateEngineFactory;

    /**
     * @param lexerFactory LexerFactory
     * @param stateEngineFactory StateEngineFactory<Handler>
     */
    public StateLexerFormatter(final LexerFactory lexerFactory, final StateEngineFactory<Handler> stateEngineFactory) {
        this.lexerFactory = lexerFactory;
        this.stateEngineFactory = stateEngineFactory;
    }

    /**
     * @param reader Reader
     * @param writer Writer
     * @throws FormatterException Something has gone wrong
     */
    @Override
    public void format(final Reader reader, final Writer writer) throws FormatterException {

        try {
            StateEngine<Handler> stateEngine = stateEngineFactory.getStateEngine();

            Lexer lexer = lexerFactory.createLexer(reader);

            while (lexer.hasNext()) {
                Token token = lexer.next();

                Handler h = stateEngine.get(token.getName());

                if (h != null) {
                    writer.write(h.handle(token.getLexeme()));
                }
            }

        } catch (IteratorException
                | StateEngineFactoryException
                | WriterException
                | HandlerException
                | LexerFactoryException e) {
            throw new FormatterException();
        }
    }
}
