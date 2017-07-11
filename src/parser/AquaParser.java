// Generated from /Users/Jesper/Documents/DTU/Bachelor thesis/biochip-compiler/src/parser/Aqua.g4 by ANTLR 4.7
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

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
		RULE_assay = 0, RULE_decls = 1, RULE_decl = 2, RULE_fluid = 3, RULE_input_ = 4, 
		RULE_var = 5, RULE_dimension = 6, RULE_conflict = 7, RULE_stmts = 8, RULE_control_stmt = 9, 
		RULE_repeat = 10, RULE_for_loop = 11, RULE_stmt = 12, RULE_assign = 13, 
		RULE_mix = 14, RULE_incubate = 15, RULE_sense = 16, RULE_sense_type = 17, 
		RULE_expr = 18, RULE_identifier = 19, RULE_index = 20;
	public static final String[] ruleNames = {
		"assay", "decls", "decl", "fluid", "input_", "var", "dimension", "conflict", 
		"stmts", "control_stmt", "repeat", "for_loop", "stmt", "assign", "mix", 
		"incubate", "sense", "sense_type", "expr", "identifier", "index"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'ASSAY'", "'START'", "'END'", "';'", "'FLUID'", "'WASH'", "'PORT'", 
		"'INPUT'", "'VAR'", "'['", "']'", "'CONFLICT'", "'FOLLOWS'", "','", "'REPEAT'", 
		"'ENDREPEAT'", "'FOR'", "'FROM'", "'TO'", "'ENDFOR'", "'='", "'MIX'", 
		"'AND'", "'IN RATIOS'", "':'", "'INCUBATE'", "'AT'", "'SENSE'", "'INTO'", 
		"'FLUORESCENCE'", "'OPTICAL'", "'*'", "'/'", "'+'", "'-'", "'('", "')'"
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(53); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(50);
					decl();
					setState(51);
					match(T__3);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(55); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		public Input_Context input_() {
			return getRuleContext(Input_Context.class,0);
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
			setState(62);
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
				input_();
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

	public static class FluidContext extends ParserRuleContext {
		public Token name;
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
			setState(64);
			match(T__4);
			setState(65);
			((FluidContext)_localctx).name = match(IDENTIFIER);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(66);
				dimension();
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(72);
				match(T__5);
				setState(73);
				match(IDENTIFIER);
				}
			}

			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(76);
				match(T__6);
				setState(77);
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

	public static class Input_Context extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AquaParser.IDENTIFIER, 0); }
		public TerminalNode INTEGER() { return getToken(AquaParser.INTEGER, 0); }
		public Input_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterInput_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitInput_(this);
		}
	}

	public final Input_Context input_() throws RecognitionException {
		Input_Context _localctx = new Input_Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_input_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__7);
			setState(81);
			match(IDENTIFIER);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER) {
				{
				setState(82);
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
			setState(85);
			match(T__8);
			setState(86);
			match(IDENTIFIER);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(87);
				dimension();
				}
				}
				setState(92);
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
			setState(93);
			match(T__9);
			setState(94);
			match(INTEGER);
			setState(95);
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
			setState(97);
			match(T__11);
			setState(98);
			match(IDENTIFIER);
			setState(99);
			_la = _input.LA(1);
			if ( !(_la==T__12 || _la==T__13) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(100);
			match(IDENTIFIER);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(101);
				match(T__5);
				setState(102);
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
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(109);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
				case T__21:
				case T__25:
				case T__27:
				case IDENTIFIER:
					{
					setState(105);
					stmt();
					setState(106);
					match(T__3);
					}
					break;
				case T__14:
				case T__16:
					{
					setState(108);
					control_stmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(111); 
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
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				repeat();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
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
			setState(117);
			match(T__14);
			setState(118);
			expr(0);
			setState(119);
			match(T__1);
			setState(120);
			stmts();
			setState(121);
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
			setState(123);
			match(T__16);
			setState(124);
			match(IDENTIFIER);
			setState(125);
			match(T__17);
			setState(126);
			expr(0);
			setState(127);
			match(T__18);
			setState(128);
			expr(0);
			setState(129);
			match(T__1);
			setState(130);
			stmts();
			setState(131);
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
			setState(138);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				assign();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				mix();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(135);
				incubate();
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
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
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
	 
		public AssignContext() { }
		public void copyFrom(AssignContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignFluidContext extends AssignContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public MixContext mix() {
			return getRuleContext(MixContext.class,0);
		}
		public IncubateContext incubate() {
			return getRuleContext(IncubateContext.class,0);
		}
		public AssignFluidContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterAssignFluid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitAssignFluid(this);
		}
	}
	public static class AssignExprContext extends AssignContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignExprContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitAssignExpr(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_assign);
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new AssignFluidContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				identifier();
				setState(141);
				match(T__20);
				setState(144);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__21:
					{
					setState(142);
					mix();
					}
					break;
				case T__25:
					{
					setState(143);
					incubate();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				_localctx = new AssignExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				identifier();
				setState(147);
				match(T__20);
				setState(148);
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
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
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
			setState(152);
			match(T__21);
			setState(153);
			identifier();
			setState(156); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(154);
				match(T__22);
				setState(155);
				identifier();
				}
				}
				setState(158); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__22 );
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(160);
				match(T__23);
				setState(161);
				expr(0);
				setState(164); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(162);
					match(T__24);
					setState(163);
					expr(0);
					}
					}
					setState(166); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__24 );
				}
			}

			setState(170);
			match(T__16);
			setState(171);
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
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
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
			setState(173);
			match(T__25);
			setState(174);
			identifier();
			setState(175);
			match(T__26);
			setState(176);
			expr(0);
			setState(177);
			match(T__16);
			setState(178);
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
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
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
			setState(180);
			match(T__27);
			setState(181);
			sense_type();
			setState(182);
			identifier();
			setState(183);
			match(T__28);
			setState(184);
			identifier();
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
			setState(186);
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
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConstExprContext extends ExprContext {
		public TerminalNode INTEGER() { return getToken(AquaParser.INTEGER, 0); }
		public ConstExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterConstExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitConstExpr(this);
		}
	}
	public static class MulDivContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulDivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitMulDiv(this);
		}
	}
	public static class AddSubContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddSubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitAddSub(this);
		}
	}
	public static class ParExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterParExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitParExpr(this);
		}
	}
	public static class VarExprContext extends ExprContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public VarExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).enterVarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AquaListener ) ((AquaListener)listener).exitVarExpr(this);
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
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__35:
				{
				_localctx = new ParExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(189);
				match(T__35);
				setState(190);
				expr(0);
				setState(191);
				match(T__36);
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(193);
				identifier();
				}
				break;
			case INTEGER:
				{
				_localctx = new ConstExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(194);
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(203);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(197);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(198);
						((MulDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__31 || _la==T__32) ) {
							((MulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(199);
						expr(6);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(200);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(201);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__33 || _la==T__34) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(202);
						expr(5);
						}
						break;
					}
					} 
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
			setState(208);
			match(IDENTIFIER);
			setState(212);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(209);
					index();
					}
					} 
				}
				setState(214);
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
			setState(215);
			match(T__9);
			setState(216);
			expr(0);
			setState(217);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u00de\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\6\38\n\3\r\3\16\39\3\4\3\4\3\4\3\4\3\4\5\4A\n\4\3\5\3\5"+
		"\3\5\7\5F\n\5\f\5\16\5I\13\5\3\5\3\5\5\5M\n\5\3\5\3\5\5\5Q\n\5\3\6\3\6"+
		"\3\6\5\6V\n\6\3\7\3\7\3\7\7\7[\n\7\f\7\16\7^\13\7\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\tj\n\t\3\n\3\n\3\n\3\n\6\np\n\n\r\n\16\nq\3\13"+
		"\3\13\5\13v\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\5\16\u008d\n\16\3\17\3\17\3\17\3\17"+
		"\5\17\u0093\n\17\3\17\3\17\3\17\3\17\5\17\u0099\n\17\3\20\3\20\3\20\3"+
		"\20\6\20\u009f\n\20\r\20\16\20\u00a0\3\20\3\20\3\20\3\20\6\20\u00a7\n"+
		"\20\r\20\16\20\u00a8\5\20\u00ab\n\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\5\24\u00c6\n\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24"+
		"\u00ce\n\24\f\24\16\24\u00d1\13\24\3\25\3\25\7\25\u00d5\n\25\f\25\16\25"+
		"\u00d8\13\25\3\26\3\26\3\26\3\26\3\26\2\3&\27\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*\2\6\3\2\17\20\3\2 !\3\2\"#\3\2$%\2\u00e4\2,\3\2"+
		"\2\2\4\67\3\2\2\2\6@\3\2\2\2\bB\3\2\2\2\nR\3\2\2\2\fW\3\2\2\2\16_\3\2"+
		"\2\2\20c\3\2\2\2\22o\3\2\2\2\24u\3\2\2\2\26w\3\2\2\2\30}\3\2\2\2\32\u008c"+
		"\3\2\2\2\34\u0098\3\2\2\2\36\u009a\3\2\2\2 \u00af\3\2\2\2\"\u00b6\3\2"+
		"\2\2$\u00bc\3\2\2\2&\u00c5\3\2\2\2(\u00d2\3\2\2\2*\u00d9\3\2\2\2,-\7\3"+
		"\2\2-.\7(\2\2./\7\4\2\2/\60\5\4\3\2\60\61\5\22\n\2\61\62\7\5\2\2\62\63"+
		"\7\2\2\3\63\3\3\2\2\2\64\65\5\6\4\2\65\66\7\6\2\2\668\3\2\2\2\67\64\3"+
		"\2\2\289\3\2\2\29\67\3\2\2\29:\3\2\2\2:\5\3\2\2\2;A\5\b\5\2<A\5\n\6\2"+
		"=A\5\f\7\2>A\5\20\t\2?A\3\2\2\2@;\3\2\2\2@<\3\2\2\2@=\3\2\2\2@>\3\2\2"+
		"\2@?\3\2\2\2A\7\3\2\2\2BC\7\7\2\2CG\7(\2\2DF\5\16\b\2ED\3\2\2\2FI\3\2"+
		"\2\2GE\3\2\2\2GH\3\2\2\2HL\3\2\2\2IG\3\2\2\2JK\7\b\2\2KM\7(\2\2LJ\3\2"+
		"\2\2LM\3\2\2\2MP\3\2\2\2NO\7\t\2\2OQ\7)\2\2PN\3\2\2\2PQ\3\2\2\2Q\t\3\2"+
		"\2\2RS\7\n\2\2SU\7(\2\2TV\7)\2\2UT\3\2\2\2UV\3\2\2\2V\13\3\2\2\2WX\7\13"+
		"\2\2X\\\7(\2\2Y[\5\16\b\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\r"+
		"\3\2\2\2^\\\3\2\2\2_`\7\f\2\2`a\7)\2\2ab\7\r\2\2b\17\3\2\2\2cd\7\16\2"+
		"\2de\7(\2\2ef\t\2\2\2fi\7(\2\2gh\7\b\2\2hj\7(\2\2ig\3\2\2\2ij\3\2\2\2"+
		"j\21\3\2\2\2kl\5\32\16\2lm\7\6\2\2mp\3\2\2\2np\5\24\13\2ok\3\2\2\2on\3"+
		"\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2r\23\3\2\2\2sv\5\26\f\2tv\5\30\r\2"+
		"us\3\2\2\2ut\3\2\2\2v\25\3\2\2\2wx\7\21\2\2xy\5&\24\2yz\7\4\2\2z{\5\22"+
		"\n\2{|\7\22\2\2|\27\3\2\2\2}~\7\23\2\2~\177\7(\2\2\177\u0080\7\24\2\2"+
		"\u0080\u0081\5&\24\2\u0081\u0082\7\25\2\2\u0082\u0083\5&\24\2\u0083\u0084"+
		"\7\4\2\2\u0084\u0085\5\22\n\2\u0085\u0086\7\26\2\2\u0086\31\3\2\2\2\u0087"+
		"\u008d\5\34\17\2\u0088\u008d\5\36\20\2\u0089\u008d\5 \21\2\u008a\u008d"+
		"\5\"\22\2\u008b\u008d\3\2\2\2\u008c\u0087\3\2\2\2\u008c\u0088\3\2\2\2"+
		"\u008c\u0089\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008b\3\2\2\2\u008d\33"+
		"\3\2\2\2\u008e\u008f\5(\25\2\u008f\u0092\7\27\2\2\u0090\u0093\5\36\20"+
		"\2\u0091\u0093\5 \21\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093\u0099"+
		"\3\2\2\2\u0094\u0095\5(\25\2\u0095\u0096\7\27\2\2\u0096\u0097\5&\24\2"+
		"\u0097\u0099\3\2\2\2\u0098\u008e\3\2\2\2\u0098\u0094\3\2\2\2\u0099\35"+
		"\3\2\2\2\u009a\u009b\7\30\2\2\u009b\u009e\5(\25\2\u009c\u009d\7\31\2\2"+
		"\u009d\u009f\5(\25\2\u009e\u009c\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e"+
		"\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00aa\3\2\2\2\u00a2\u00a3\7\32\2\2"+
		"\u00a3\u00a6\5&\24\2\u00a4\u00a5\7\33\2\2\u00a5\u00a7\5&\24\2\u00a6\u00a4"+
		"\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a2\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2"+
		"\2\2\u00ac\u00ad\7\23\2\2\u00ad\u00ae\5&\24\2\u00ae\37\3\2\2\2\u00af\u00b0"+
		"\7\34\2\2\u00b0\u00b1\5(\25\2\u00b1\u00b2\7\35\2\2\u00b2\u00b3\5&\24\2"+
		"\u00b3\u00b4\7\23\2\2\u00b4\u00b5\5&\24\2\u00b5!\3\2\2\2\u00b6\u00b7\7"+
		"\36\2\2\u00b7\u00b8\5$\23\2\u00b8\u00b9\5(\25\2\u00b9\u00ba\7\37\2\2\u00ba"+
		"\u00bb\5(\25\2\u00bb#\3\2\2\2\u00bc\u00bd\t\3\2\2\u00bd%\3\2\2\2\u00be"+
		"\u00bf\b\24\1\2\u00bf\u00c0\7&\2\2\u00c0\u00c1\5&\24\2\u00c1\u00c2\7\'"+
		"\2\2\u00c2\u00c6\3\2\2\2\u00c3\u00c6\5(\25\2\u00c4\u00c6\7)\2\2\u00c5"+
		"\u00be\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00cf\3\2"+
		"\2\2\u00c7\u00c8\f\7\2\2\u00c8\u00c9\t\4\2\2\u00c9\u00ce\5&\24\b\u00ca"+
		"\u00cb\f\6\2\2\u00cb\u00cc\t\5\2\2\u00cc\u00ce\5&\24\7\u00cd\u00c7\3\2"+
		"\2\2\u00cd\u00ca\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0\'\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d6\7(\2\2"+
		"\u00d3\u00d5\5*\26\2\u00d4\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4"+
		"\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7)\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9"+
		"\u00da\7\f\2\2\u00da\u00db\5&\24\2\u00db\u00dc\7\r\2\2\u00dc+\3\2\2\2"+
		"\279@GLPU\\ioqu\u008c\u0092\u0098\u00a0\u00a8\u00aa\u00c5\u00cd\u00cf"+
		"\u00d6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}