// Generated from TESLA.g4 by ANTLR 4.5
package trex.ruleparser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TESLALexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, ASSIGN=17, 
		DEFINE=18, FROM=19, WHERE=20, CONSUMING=21, VALTYPE=22, SEL_POLICY=23, 
		AGGR_FUN=24, OPERATOR=25, BINOP_MUL=26, BINOP_ADD=27, INT_VAL=28, FLOAT_VAL=29, 
		BOOL_VAL=30, STRING_VAL=31, EVT_NAME=32, ATTR_NAME=33, PARAM_NAME=34, 
		WS=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "ASSIGN", 
		"DEFINE", "FROM", "WHERE", "CONSUMING", "VALTYPE", "SEL_POLICY", "AGGR_FUN", 
		"OPERATOR", "BINOP_MUL", "BINOP_ADD", "INT_VAL", "FLOAT_VAL", "BOOL_VAL", 
		"STRING_VAL", "EVT_NAME", "ATTR_NAME", "PARAM_NAME", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'=>'", "'within'", "'from'", "'between'", "'and'", "'('", 
		"','", "')'", "':'", "':='", "'['", "']'", "'as'", "'not'", "';'", "'Assign'", 
		"'Define'", "'From'", "'Where'", "'Consuming'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "ASSIGN", "DEFINE", "FROM", "WHERE", "CONSUMING", 
		"VALTYPE", "SEL_POLICY", "AGGR_FUN", "OPERATOR", "BINOP_MUL", "BINOP_ADD", 
		"INT_VAL", "FLOAT_VAL", "BOOL_VAL", "STRING_VAL", "EVT_NAME", "ATTR_NAME", 
		"PARAM_NAME", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	StringBuilder buf = new StringBuilder(); // can't make locals in lexer rules


	public TESLALexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TESLA.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 30:
			STRING_VAL_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_VAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			buf.append('\r');
			break;
		case 1:
			buf.append('\n');
			break;
		case 2:
			buf.append('\t');
			break;
		case 3:
			buf.append('\\');
			break;
		case 4:
			buf.append('"');
			break;
		case 5:
			buf.append((char)_input.LA(-1));
			break;
		case 6:
			setText(buf.toString()); buf.setLength(0); System.out.println(getText());
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2%\u0135\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\5\27\u00b4\n\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00c3\n\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31"+
		"\u00d6\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u00e0\n\32\3"+
		"\33\3\33\3\34\3\34\3\35\6\35\u00e7\n\35\r\35\16\35\u00e8\3\36\6\36\u00ec"+
		"\n\36\r\36\16\36\u00ed\3\36\3\36\6\36\u00f2\n\36\r\36\16\36\u00f3\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u00ff\n\37\3 \3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \5 \u010d\n \3 \3 \7 \u0111\n \f \16 \u0114\13 "+
		"\3 \3 \3 \3!\3!\7!\u011b\n!\f!\16!\u011e\13!\3\"\3\"\7\"\u0122\n\"\f\""+
		"\16\"\u0125\13\"\3#\3#\3#\7#\u012a\n#\f#\16#\u012d\13#\3$\6$\u0130\n$"+
		"\r$\16$\u0131\3$\3$\2\2%\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63"+
		"\33\65\34\67\359\36;\37= ?!A\"C#E$G%\3\2\b\4\2((~~\4\2,,\61\61\4\2--/"+
		"/\4\2$$^^\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\u014f\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\3I\3\2\2\2\5K\3\2\2"+
		"\2\7N\3\2\2\2\tU\3\2\2\2\13Z\3\2\2\2\rb\3\2\2\2\17f\3\2\2\2\21h\3\2\2"+
		"\2\23j\3\2\2\2\25l\3\2\2\2\27n\3\2\2\2\31q\3\2\2\2\33s\3\2\2\2\35u\3\2"+
		"\2\2\37x\3\2\2\2!|\3\2\2\2#~\3\2\2\2%\u0085\3\2\2\2\'\u008c\3\2\2\2)\u0091"+
		"\3\2\2\2+\u0097\3\2\2\2-\u00b3\3\2\2\2/\u00c2\3\2\2\2\61\u00d5\3\2\2\2"+
		"\63\u00df\3\2\2\2\65\u00e1\3\2\2\2\67\u00e3\3\2\2\29\u00e6\3\2\2\2;\u00eb"+
		"\3\2\2\2=\u00fe\3\2\2\2?\u0100\3\2\2\2A\u0118\3\2\2\2C\u011f\3\2\2\2E"+
		"\u0126\3\2\2\2G\u012f\3\2\2\2IJ\7\60\2\2J\4\3\2\2\2KL\7?\2\2LM\7@\2\2"+
		"M\6\3\2\2\2NO\7y\2\2OP\7k\2\2PQ\7v\2\2QR\7j\2\2RS\7k\2\2ST\7p\2\2T\b\3"+
		"\2\2\2UV\7h\2\2VW\7t\2\2WX\7q\2\2XY\7o\2\2Y\n\3\2\2\2Z[\7d\2\2[\\\7g\2"+
		"\2\\]\7v\2\2]^\7y\2\2^_\7g\2\2_`\7g\2\2`a\7p\2\2a\f\3\2\2\2bc\7c\2\2c"+
		"d\7p\2\2de\7f\2\2e\16\3\2\2\2fg\7*\2\2g\20\3\2\2\2hi\7.\2\2i\22\3\2\2"+
		"\2jk\7+\2\2k\24\3\2\2\2lm\7<\2\2m\26\3\2\2\2no\7<\2\2op\7?\2\2p\30\3\2"+
		"\2\2qr\7]\2\2r\32\3\2\2\2st\7_\2\2t\34\3\2\2\2uv\7c\2\2vw\7u\2\2w\36\3"+
		"\2\2\2xy\7p\2\2yz\7q\2\2z{\7v\2\2{ \3\2\2\2|}\7=\2\2}\"\3\2\2\2~\177\7"+
		"C\2\2\177\u0080\7u\2\2\u0080\u0081\7u\2\2\u0081\u0082\7k\2\2\u0082\u0083"+
		"\7i\2\2\u0083\u0084\7p\2\2\u0084$\3\2\2\2\u0085\u0086\7F\2\2\u0086\u0087"+
		"\7g\2\2\u0087\u0088\7h\2\2\u0088\u0089\7k\2\2\u0089\u008a\7p\2\2\u008a"+
		"\u008b\7g\2\2\u008b&\3\2\2\2\u008c\u008d\7H\2\2\u008d\u008e\7t\2\2\u008e"+
		"\u008f\7q\2\2\u008f\u0090\7o\2\2\u0090(\3\2\2\2\u0091\u0092\7Y\2\2\u0092"+
		"\u0093\7j\2\2\u0093\u0094\7g\2\2\u0094\u0095\7t\2\2\u0095\u0096\7g\2\2"+
		"\u0096*\3\2\2\2\u0097\u0098\7E\2\2\u0098\u0099\7q\2\2\u0099\u009a\7p\2"+
		"\2\u009a\u009b\7u\2\2\u009b\u009c\7w\2\2\u009c\u009d\7o\2\2\u009d\u009e"+
		"\7k\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7i\2\2\u00a0,\3\2\2\2\u00a1\u00a2"+
		"\7u\2\2\u00a2\u00a3\7v\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7k\2\2\u00a5"+
		"\u00a6\7p\2\2\u00a6\u00b4\7i\2\2\u00a7\u00a8\7k\2\2\u00a8\u00a9\7p\2\2"+
		"\u00a9\u00b4\7v\2\2\u00aa\u00ab\7h\2\2\u00ab\u00ac\7n\2\2\u00ac\u00ad"+
		"\7q\2\2\u00ad\u00ae\7c\2\2\u00ae\u00b4\7v\2\2\u00af\u00b0\7d\2\2\u00b0"+
		"\u00b1\7q\2\2\u00b1\u00b2\7q\2\2\u00b2\u00b4\7n\2\2\u00b3\u00a1\3\2\2"+
		"\2\u00b3\u00a7\3\2\2\2\u00b3\u00aa\3\2\2\2\u00b3\u00af\3\2\2\2\u00b4."+
		"\3\2\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7e\2\2\u00b8"+
		"\u00c3\7j\2\2\u00b9\u00ba\7n\2\2\u00ba\u00bb\7c\2\2\u00bb\u00bc\7u\2\2"+
		"\u00bc\u00c3\7v\2\2\u00bd\u00be\7h\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0"+
		"\7t\2\2\u00c0\u00c1\7u\2\2\u00c1\u00c3\7v\2\2\u00c2\u00b5\3\2\2\2\u00c2"+
		"\u00b9\3\2\2\2\u00c2\u00bd\3\2\2\2\u00c3\60\3\2\2\2\u00c4\u00c5\7C\2\2"+
		"\u00c5\u00c6\7X\2\2\u00c6\u00d6\7I\2\2\u00c7\u00c8\7U\2\2\u00c8\u00c9"+
		"\7W\2\2\u00c9\u00d6\7O\2\2\u00ca\u00cb\7O\2\2\u00cb\u00cc\7C\2\2\u00cc"+
		"\u00d6\7Z\2\2\u00cd\u00ce\7O\2\2\u00ce\u00cf\7K\2\2\u00cf\u00d6\7P\2\2"+
		"\u00d0\u00d1\7E\2\2\u00d1\u00d2\7Q\2\2\u00d2\u00d3\7W\2\2\u00d3\u00d4"+
		"\7P\2\2\u00d4\u00d6\7V\2\2\u00d5\u00c4\3\2\2\2\u00d5\u00c7\3\2\2\2\u00d5"+
		"\u00ca\3\2\2\2\u00d5\u00cd\3\2\2\2\u00d5\u00d0\3\2\2\2\u00d6\62\3\2\2"+
		"\2\u00d7\u00e0\4>@\2\u00d8\u00d9\7@\2\2\u00d9\u00e0\7?\2\2\u00da\u00db"+
		"\7>\2\2\u00db\u00e0\7?\2\2\u00dc\u00dd\7#\2\2\u00dd\u00e0\7?\2\2\u00de"+
		"\u00e0\t\2\2\2\u00df\u00d7\3\2\2\2\u00df\u00d8\3\2\2\2\u00df\u00da\3\2"+
		"\2\2\u00df\u00dc\3\2\2\2\u00df\u00de\3\2\2\2\u00e0\64\3\2\2\2\u00e1\u00e2"+
		"\t\3\2\2\u00e2\66\3\2\2\2\u00e3\u00e4\t\4\2\2\u00e48\3\2\2\2\u00e5\u00e7"+
		"\4\62;\2\u00e6\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9:\3\2\2\2\u00ea\u00ec\4\62;\2\u00eb\u00ea\3\2\2\2"+
		"\u00ec\u00ed\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef"+
		"\3\2\2\2\u00ef\u00f1\7\60\2\2\u00f0\u00f2\4\62;\2\u00f1\u00f0\3\2\2\2"+
		"\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4<\3"+
		"\2\2\2\u00f5\u00f6\7h\2\2\u00f6\u00f7\7c\2\2\u00f7\u00f8\7n\2\2\u00f8"+
		"\u00f9\7u\2\2\u00f9\u00ff\7g\2\2\u00fa\u00fb\7v\2\2\u00fb\u00fc\7t\2\2"+
		"\u00fc\u00fd\7w\2\2\u00fd\u00ff\7g\2\2\u00fe\u00f5\3\2\2\2\u00fe\u00fa"+
		"\3\2\2\2\u00ff>\3\2\2\2\u0100\u0112\7$\2\2\u0101\u010c\7^\2\2\u0102\u0103"+
		"\7t\2\2\u0103\u010d\b \2\2\u0104\u0105\7p\2\2\u0105\u010d\b \3\2\u0106"+
		"\u0107\7v\2\2\u0107\u010d\b \4\2\u0108\u0109\7^\2\2\u0109\u010d\b \5\2"+
		"\u010a\u010b\7$\2\2\u010b\u010d\b \6\2\u010c\u0102\3\2\2\2\u010c\u0104"+
		"\3\2\2\2\u010c\u0106\3\2\2\2\u010c\u0108\3\2\2\2\u010c\u010a\3\2\2\2\u010d"+
		"\u0111\3\2\2\2\u010e\u010f\n\5\2\2\u010f\u0111\b \7\2\u0110\u0101\3\2"+
		"\2\2\u0110\u010e\3\2\2\2\u0111\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0115\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0116\7$"+
		"\2\2\u0116\u0117\b \b\2\u0117@\3\2\2\2\u0118\u011c\4C\\\2\u0119\u011b"+
		"\t\6\2\2\u011a\u0119\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011c"+
		"\u011d\3\2\2\2\u011dB\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0123\4c|\2\u0120"+
		"\u0122\t\6\2\2\u0121\u0120\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2"+
		"\2\2\u0123\u0124\3\2\2\2\u0124D\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u0127"+
		"\7&\2\2\u0127\u012b\4c|\2\u0128\u012a\t\6\2\2\u0129\u0128\3\2\2\2\u012a"+
		"\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012cF\3\2\2\2"+
		"\u012d\u012b\3\2\2\2\u012e\u0130\t\7\2\2\u012f\u012e\3\2\2\2\u0130\u0131"+
		"\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\3\2\2\2\u0133"+
		"\u0134\b$\t\2\u0134H\3\2\2\2\25\2\u00b3\u00c2\u00d5\u00df\u00e8\u00ed"+
		"\u00f3\u00fe\u010c\u0110\u0112\u011a\u011c\u0121\u0123\u0129\u012b\u0131"+
		"\n\3 \2\3 \3\3 \4\3 \5\3 \6\3 \7\3 \b\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}