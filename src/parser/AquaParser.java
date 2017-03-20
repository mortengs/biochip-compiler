// Generated from /Users/Jesper/Documents/DTU/Bachelor thesis/biochip-compiler/src/parser/Aqua.g4 by ANTLR 4.6
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AquaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, IDENTIFIER=38, 
		INTEGER=39, WS=40;
	public static final int
		RULE_assay = 0, RULE_decls = 1, RULE_decl = 2, RULE_fluid = 3, RULE_input = 4, 
		RULE_var = 5, RULE_dimension = 6, RULE_conflict = 7, RULE_stmts = 8, RULE_control_stmt = 9, 
		RULE_repeat = 10, RULE_for_loop = 11, RULE_stmt = 12, RULE_assign = 13, 
		RULE_mix = 14, RULE_incubate = 15, RULE_sense = 16, RULE_sense_type = 17, 
		RULE_expr = 18, RULE_identifier = 19, RULE_index = 20;
	public static final String[] ruleNames = {
		"assay", "decls", "decl", "fluid", "input", "var", "dimension", "conflict", 
		"stmts", "control_stmt", "repeat", "for_loop", "stmt", "assign", "mix", 
		"incubate", "sense", "sense_type", "expr", "identifier", "index"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'ASSAY'", "'START'", "'END'", "';'", "'FLUID'", "'WASH'", "'PORT'", 
		"'INPUT'", "'VAR'", "'['", "']'", "'CONFLICT'", "'FOLLOWS'", "','", "'REPEAT'", 
		"'ENDREPEAT'", "'FOR'", "'FROM'", "'TO'", "'ENDFOR'", "'='", "'MIX'", 
		"'AND'", "'IN RATIOS'", "':'", "'INCUBATE'", "'AT'", "'SENSE'", "'INTO'", 
		"'FLUORESCENCE'", "'OPTICAL'", "'*'", "'/'", "'x'", "'-'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "IDENTIFIER", "INTEGER", "WS"
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

	@Override
	public String getGrammarFileName() { return "Aqua.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AquaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AssayContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AquaParser.IDENTIFIER, 0); }
		public DeclsContext decls() {
			return getRuleContext(DeclsContext.class,0);
		}
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(AquaParser.EOF, 0); }
		public AssayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assay; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterAssay(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitAssay(this);
		}
	}

	public final AssayContext assay() throws RecognitionException {
		AssayContext _localctx = new AssayContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_assay);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(T__0);
			setState(43);
			match(IDENTIFIER);
			setState(44);
			match(T__1);
			setState(45);
			decls();
			setState(46);
			stmts();
			setState(47);
			match(T__2);
			setState(48);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclsContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public DeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterDecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitDecls(this);
		}
	}

	public final DeclsContext decls() throws RecognitionException {
		DeclsContext _localctx = new DeclsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				decl();
				setState(51);
				match(T__3);
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << T__8) | (1L << T__11))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public FluidContext fluid() {
			return getRuleContext(FluidContext.class,0);
		}
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public ConflictContext conflict() {
			return getRuleContext(ConflictContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				fluid();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				input();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
				var();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(60);
				conflict();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FluidContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(AquaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(AquaParser.IDENTIFIER, i);
		}
		public List<DimensionContext> dimension() {
			return getRuleContexts(DimensionContext.class);
		}
		public DimensionContext dimension(int i) {
			return getRuleContext(DimensionContext.class,i);
		}
		public TerminalNode INTEGER() { return getToken(AquaParser.INTEGER, 0); }
		public FluidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fluid; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterFluid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitFluid(this);
		}
	}

	public final FluidContext fluid() throws RecognitionException {
		FluidContext _localctx = new FluidContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fluid);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__4);
			setState(64);
			match(IDENTIFIER);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(65);
				dimension();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(71);
				match(T__5);
				setState(72);
				match(IDENTIFIER);
				}
			}

			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(75);
				match(T__6);
				setState(76);
				match(INTEGER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InputContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AquaParser.IDENTIFIER, 0); }
		public TerminalNode INTEGER() { return getToken(AquaParser.INTEGER, 0); }
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitInput(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__7);
			setState(80);
			match(IDENTIFIER);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER) {
				{
				setState(81);
				match(INTEGER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AquaParser.IDENTIFIER, 0); }
		public List<DimensionContext> dimension() {
			return getRuleContexts(DimensionContext.class);
		}
		public DimensionContext dimension(int i) {
			return getRuleContext(DimensionContext.class,i);
		}
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitVar(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__8);
			setState(85);
			match(IDENTIFIER);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(86);
				dimension();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DimensionContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(AquaParser.INTEGER, 0); }
		public DimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterDimension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitDimension(this);
		}
	}

	public final DimensionContext dimension() throws RecognitionException {
		DimensionContext _localctx = new DimensionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dimension);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__9);
			setState(93);
			match(INTEGER);
			setState(94);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConflictContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(AquaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(AquaParser.IDENTIFIER, i);
		}
		public ConflictContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conflict; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterConflict(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitConflict(this);
		}
	}

	public final ConflictContext conflict() throws RecognitionException {
		ConflictContext _localctx = new ConflictContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_conflict);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__11);
			setState(97);
			match(IDENTIFIER);
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				{
				setState(98);
				match(T__12);
				setState(99);
				match(IDENTIFIER);
				}
				break;
			case T__13:
				{
				setState(100);
				match(T__13);
				setState(101);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(104);
				match(T__5);
				setState(105);
				match(IDENTIFIER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtsContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<Control_stmtContext> control_stmt() {
			return getRuleContexts(Control_stmtContext.class);
		}
		public Control_stmtContext control_stmt(int i) {
			return getRuleContext(Control_stmtContext.class,i);
		}
		public StmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterStmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitStmts(this);
		}
	}

	public final StmtsContext stmts() throws RecognitionException {
		StmtsContext _localctx = new StmtsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stmts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(112);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
				case T__21:
				case T__25:
				case T__27:
				case IDENTIFIER:
					{
					setState(108);
					stmt();
					setState(109);
					match(T__3);
					}
					break;
				case T__14:
				case T__16:
					{
					setState(111);
					control_stmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__14) | (1L << T__16) | (1L << T__21) | (1L << T__25) | (1L << T__27) | (1L << IDENTIFIER))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Control_stmtContext extends ParserRuleContext {
		public RepeatContext repeat() {
			return getRuleContext(RepeatContext.class,0);
		}
		public For_loopContext for_loop() {
			return getRuleContext(For_loopContext.class,0);
		}
		public Control_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_control_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterControl_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitControl_stmt(this);
		}
	}

	public final Control_stmtContext control_stmt() throws RecognitionException {
		Control_stmtContext _localctx = new Control_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_control_stmt);
		try {
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				repeat();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				for_loop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepeatContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public RepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterRepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitRepeat(this);
		}
	}

	public final RepeatContext repeat() throws RecognitionException {
		RepeatContext _localctx = new RepeatContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_repeat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__14);
			setState(121);
			expr(0);
			setState(122);
			match(T__1);
			setState(123);
			stmts();
			setState(124);
			match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_loopContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AquaParser.IDENTIFIER, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public For_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterFor_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitFor_loop(this);
		}
	}

	public final For_loopContext for_loop() throws RecognitionException {
		For_loopContext _localctx = new For_loopContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_for_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__16);
			setState(127);
			match(IDENTIFIER);
			setState(128);
			match(T__17);
			setState(129);
			expr(0);
			setState(130);
			match(T__18);
			setState(131);
			expr(0);
			setState(132);
			match(T__1);
			setState(133);
			stmts();
			setState(134);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public MixContext mix() {
			return getRuleContext(MixContext.class,0);
		}
		public IncubateContext incubate() {
			return getRuleContext(IncubateContext.class,0);
		}
		public SenseContext sense() {
			return getRuleContext(SenseContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stmt);
		try {
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				assign();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				mix();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				incubate();
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				sense();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 5);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AquaParser.IDENTIFIER, 0); }
		public MixContext mix() {
			return getRuleContext(MixContext.class,0);
		}
		public IncubateContext incubate() {
			return getRuleContext(IncubateContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitAssign(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_assign);
		try {
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				match(IDENTIFIER);
				setState(144);
				match(T__20);
				setState(147);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__21:
					{
					setState(145);
					mix();
					}
					break;
				case T__25:
					{
					setState(146);
					incubate();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(IDENTIFIER);
				setState(150);
				match(T__20);
				setState(151);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MixContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(AquaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(AquaParser.IDENTIFIER, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterMix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitMix(this);
		}
	}

	public final MixContext mix() throws RecognitionException {
		MixContext _localctx = new MixContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_mix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(T__21);
			setState(155);
			match(IDENTIFIER);
			setState(158); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(156);
				match(T__22);
				setState(157);
				match(IDENTIFIER);
				}
				}
				setState(160); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__22 );
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(162);
				match(T__23);
				setState(163);
				expr(0);
				setState(166); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(164);
					match(T__24);
					setState(165);
					expr(0);
					}
					}
					setState(168); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__24 );
				}
			}

			setState(172);
			match(T__16);
			setState(173);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IncubateContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AquaParser.IDENTIFIER, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IncubateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incubate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterIncubate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitIncubate(this);
		}
	}

	public final IncubateContext incubate() throws RecognitionException {
		IncubateContext _localctx = new IncubateContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_incubate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(T__25);
			setState(176);
			match(IDENTIFIER);
			setState(177);
			match(T__26);
			setState(178);
			expr(0);
			setState(179);
			match(T__16);
			setState(180);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SenseContext extends ParserRuleContext {
		public Sense_typeContext sense_type() {
			return getRuleContext(Sense_typeContext.class,0);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(AquaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(AquaParser.IDENTIFIER, i);
		}
		public SenseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sense; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterSense(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitSense(this);
		}
	}

	public final SenseContext sense() throws RecognitionException {
		SenseContext _localctx = new SenseContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_sense);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(T__27);
			setState(183);
			sense_type();
			setState(184);
			match(IDENTIFIER);
			setState(185);
			match(T__28);
			setState(186);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sense_typeContext extends ParserRuleContext {
		public Sense_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sense_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterSense_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitSense_type(this);
		}
	}

	public final Sense_typeContext sense_type() throws RecognitionException {
		Sense_typeContext _localctx = new Sense_typeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_sense_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_la = _input.LA(1);
			if ( !(_la==T__29 || _la==T__30) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(AquaParser.INTEGER, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__35:
				{
				setState(191);
				match(T__35);
				setState(192);
				expr(0);
				setState(193);
				match(T__36);
				}
				break;
			case IDENTIFIER:
				{
				setState(195);
				identifier();
				}
				break;
			case INTEGER:
				{
				setState(196);
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(207);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(205);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(199);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(200);
						_la = _input.LA(1);
						if ( !(_la==T__31 || _la==T__32) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(201);
						expr(6);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(202);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(203);
						_la = _input.LA(1);
						if ( !(_la==T__33 || _la==T__34) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(204);
						expr(5);
						}
						break;
					}
					} 
				}
				setState(209);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AquaParser.IDENTIFIER, 0); }
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_identifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(IDENTIFIER);
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(211);
					index();
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitIndex(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(T__9);
			setState(218);
			expr(0);
			setState(219);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 18:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3*\u00e0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\6\38\n\3\r\3\16\39\3\4\3\4\3\4\3\4\5\4@\n\4\3\5\3\5\3\5"+
		"\7\5E\n\5\f\5\16\5H\13\5\3\5\3\5\5\5L\n\5\3\5\3\5\5\5P\n\5\3\6\3\6\3\6"+
		"\5\6U\n\6\3\7\3\7\3\7\7\7Z\n\7\f\7\16\7]\13\7\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\5\ti\n\t\3\t\3\t\5\tm\n\t\3\n\3\n\3\n\3\n\6\ns\n\n\r"+
		"\n\16\nt\3\13\3\13\5\13y\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\5\16\u0090\n\16\3\17"+
		"\3\17\3\17\3\17\5\17\u0096\n\17\3\17\3\17\3\17\5\17\u009b\n\17\3\20\3"+
		"\20\3\20\3\20\6\20\u00a1\n\20\r\20\16\20\u00a2\3\20\3\20\3\20\3\20\6\20"+
		"\u00a9\n\20\r\20\16\20\u00aa\5\20\u00ad\n\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00c8\n\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\7\24\u00d0\n\24\f\24\16\24\u00d3\13\24\3\25\3\25\7\25\u00d7\n\25"+
		"\f\25\16\25\u00da\13\25\3\26\3\26\3\26\3\26\3\26\2\3&\27\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*\2\5\3\2 !\3\2\"#\3\2$%\u00e6\2,\3\2\2"+
		"\2\4\67\3\2\2\2\6?\3\2\2\2\bA\3\2\2\2\nQ\3\2\2\2\fV\3\2\2\2\16^\3\2\2"+
		"\2\20b\3\2\2\2\22r\3\2\2\2\24x\3\2\2\2\26z\3\2\2\2\30\u0080\3\2\2\2\32"+
		"\u008f\3\2\2\2\34\u009a\3\2\2\2\36\u009c\3\2\2\2 \u00b1\3\2\2\2\"\u00b8"+
		"\3\2\2\2$\u00be\3\2\2\2&\u00c7\3\2\2\2(\u00d4\3\2\2\2*\u00db\3\2\2\2,"+
		"-\7\3\2\2-.\7(\2\2./\7\4\2\2/\60\5\4\3\2\60\61\5\22\n\2\61\62\7\5\2\2"+
		"\62\63\7\2\2\3\63\3\3\2\2\2\64\65\5\6\4\2\65\66\7\6\2\2\668\3\2\2\2\67"+
		"\64\3\2\2\289\3\2\2\29\67\3\2\2\29:\3\2\2\2:\5\3\2\2\2;@\5\b\5\2<@\5\n"+
		"\6\2=@\5\f\7\2>@\5\20\t\2?;\3\2\2\2?<\3\2\2\2?=\3\2\2\2?>\3\2\2\2@\7\3"+
		"\2\2\2AB\7\7\2\2BF\7(\2\2CE\5\16\b\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3"+
		"\2\2\2GK\3\2\2\2HF\3\2\2\2IJ\7\b\2\2JL\7(\2\2KI\3\2\2\2KL\3\2\2\2LO\3"+
		"\2\2\2MN\7\t\2\2NP\7)\2\2OM\3\2\2\2OP\3\2\2\2P\t\3\2\2\2QR\7\n\2\2RT\7"+
		"(\2\2SU\7)\2\2TS\3\2\2\2TU\3\2\2\2U\13\3\2\2\2VW\7\13\2\2W[\7(\2\2XZ\5"+
		"\16\b\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\r\3\2\2\2][\3\2\2\2"+
		"^_\7\f\2\2_`\7)\2\2`a\7\r\2\2a\17\3\2\2\2bc\7\16\2\2ch\7(\2\2de\7\17\2"+
		"\2ei\7(\2\2fg\7\20\2\2gi\7(\2\2hd\3\2\2\2hf\3\2\2\2il\3\2\2\2jk\7\b\2"+
		"\2km\7(\2\2lj\3\2\2\2lm\3\2\2\2m\21\3\2\2\2no\5\32\16\2op\7\6\2\2ps\3"+
		"\2\2\2qs\5\24\13\2rn\3\2\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2u"+
		"\23\3\2\2\2vy\5\26\f\2wy\5\30\r\2xv\3\2\2\2xw\3\2\2\2y\25\3\2\2\2z{\7"+
		"\21\2\2{|\5&\24\2|}\7\4\2\2}~\5\22\n\2~\177\7\22\2\2\177\27\3\2\2\2\u0080"+
		"\u0081\7\23\2\2\u0081\u0082\7(\2\2\u0082\u0083\7\24\2\2\u0083\u0084\5"+
		"&\24\2\u0084\u0085\7\25\2\2\u0085\u0086\5&\24\2\u0086\u0087\7\4\2\2\u0087"+
		"\u0088\5\22\n\2\u0088\u0089\7\26\2\2\u0089\31\3\2\2\2\u008a\u0090\5\34"+
		"\17\2\u008b\u0090\5\36\20\2\u008c\u0090\5 \21\2\u008d\u0090\5\"\22\2\u008e"+
		"\u0090\3\2\2\2\u008f\u008a\3\2\2\2\u008f\u008b\3\2\2\2\u008f\u008c\3\2"+
		"\2\2\u008f\u008d\3\2\2\2\u008f\u008e\3\2\2\2\u0090\33\3\2\2\2\u0091\u0092"+
		"\7(\2\2\u0092\u0095\7\27\2\2\u0093\u0096\5\36\20\2\u0094\u0096\5 \21\2"+
		"\u0095\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096\u009b\3\2\2\2\u0097\u0098"+
		"\7(\2\2\u0098\u0099\7\27\2\2\u0099\u009b\5&\24\2\u009a\u0091\3\2\2\2\u009a"+
		"\u0097\3\2\2\2\u009b\35\3\2\2\2\u009c\u009d\7\30\2\2\u009d\u00a0\7(\2"+
		"\2\u009e\u009f\7\31\2\2\u009f\u00a1\7(\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2"+
		"\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00ac\3\2\2\2\u00a4"+
		"\u00a5\7\32\2\2\u00a5\u00a8\5&\24\2\u00a6\u00a7\7\33\2\2\u00a7\u00a9\5"+
		"&\24\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa"+
		"\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00a4\3\2\2\2\u00ac\u00ad\3\2"+
		"\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\7\23\2\2\u00af\u00b0\5&\24\2\u00b0"+
		"\37\3\2\2\2\u00b1\u00b2\7\34\2\2\u00b2\u00b3\7(\2\2\u00b3\u00b4\7\35\2"+
		"\2\u00b4\u00b5\5&\24\2\u00b5\u00b6\7\23\2\2\u00b6\u00b7\5&\24\2\u00b7"+
		"!\3\2\2\2\u00b8\u00b9\7\36\2\2\u00b9\u00ba\5$\23\2\u00ba\u00bb\7(\2\2"+
		"\u00bb\u00bc\7\37\2\2\u00bc\u00bd\7(\2\2\u00bd#\3\2\2\2\u00be\u00bf\t"+
		"\2\2\2\u00bf%\3\2\2\2\u00c0\u00c1\b\24\1\2\u00c1\u00c2\7&\2\2\u00c2\u00c3"+
		"\5&\24\2\u00c3\u00c4\7\'\2\2\u00c4\u00c8\3\2\2\2\u00c5\u00c8\5(\25\2\u00c6"+
		"\u00c8\7)\2\2\u00c7\u00c0\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c6\3\2"+
		"\2\2\u00c8\u00d1\3\2\2\2\u00c9\u00ca\f\7\2\2\u00ca\u00cb\t\3\2\2\u00cb"+
		"\u00d0\5&\24\b\u00cc\u00cd\f\6\2\2\u00cd\u00ce\t\4\2\2\u00ce\u00d0\5&"+
		"\24\7\u00cf\u00c9\3\2\2\2\u00cf\u00cc\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\'\3\2\2\2\u00d3\u00d1\3\2\2\2"+
		"\u00d4\u00d8\7(\2\2\u00d5\u00d7\5*\26\2\u00d6\u00d5\3\2\2\2\u00d7\u00da"+
		"\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9)\3\2\2\2\u00da"+
		"\u00d8\3\2\2\2\u00db\u00dc\7\f\2\2\u00dc\u00dd\5&\24\2\u00dd\u00de\7\r"+
		"\2\2\u00de+\3\2\2\2\309?FKOT[hlrtx\u008f\u0095\u009a\u00a2\u00aa\u00ac"+
		"\u00c7\u00cf\u00d1\u00d8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}