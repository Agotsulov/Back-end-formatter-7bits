package it.sevenbits.formatters.state;

import it.sevenbits.core.Formatter;
import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.state.handlers.HandlerException;
import it.sevenbits.formatters.state.lexers.factories.LexerFactory;
import it.sevenbits.formatters.state.lexers.factories.LexerFactoryException;
import it.sevenbits.formatters.state.lexers.Lexer;
import it.sevenbits.formatters.state.sm.factories.StateEngineFactory;
import it.sevenbits.formatters.state.sm.factories.StateEngineFactoryException;
import it.sevenbits.formatters.state.tokens.Token;
import it.sevenbits.formatters.state.handlers.Handler;
import it.sevenbits.formatters.state.sm.StateEngine;
import it.sevenbits.io.Reader;
import it.sevenbits.io.Writer;
import it.sevenbits.io.WriterException;
import it.sevenbits.other.IteratorException;

/**
 *Доделываем свой maven проект, проект должен корректно собираться и запускаться через java -jar
 * Доделываем интерфейсы
 * Доделываем тесты
 * Доделываем лексер
 * Не забываем про SOLID и замечания
 * Доделываем автоматы
 * Обязательное задание:
 * Lexer-автомат
 * Formatter-автомат
 * Добавить обработку строковых литералов
 * Добавить обработку однострочных комментариев
 * Сделать покрытие тестами по проекту не менее 90%
 *
 * Дополнительное (необязательное) задание за бонусные баллы:
 * Добавить обработку многострочных комментариев
 * Вынести репозитории переходов и команд (конфигурацию автомата) в файл с настройками
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
            //StateEngine<Token> lexer = null;
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
