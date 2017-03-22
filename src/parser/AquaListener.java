// Generated from /Users/Jesper/Documents/DTU/Bachelor thesis/biochip-compiler/src/parser/Aqua.g4 by ANTLR 4.6
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AquaParser}.
 */
public interface AquaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AquaParser#assay}.
	 * @param ctx the parse tree
	 */
	void enterAssay(AquaParser.AssayContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#assay}.
	 * @param ctx the parse tree
	 */
	void exitAssay(AquaParser.AssayContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#decls}.
	 * @param ctx the parse tree
	 */
	void enterDecls(AquaParser.DeclsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#decls}.
	 * @param ctx the parse tree
	 */
	void exitDecls(AquaParser.DeclsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(AquaParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(AquaParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#fluid}.
	 * @param ctx the parse tree
	 */
	void enterFluid(AquaParser.FluidContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#fluid}.
	 * @param ctx the parse tree
	 */
	void exitFluid(AquaParser.FluidContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#input_}.
	 * @param ctx the parse tree
	 */
	void enterInput_(AquaParser.Input_Context ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#input_}.
	 * @param ctx the parse tree
	 */
	void exitInput_(AquaParser.Input_Context ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(AquaParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(AquaParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#dimension}.
	 * @param ctx the parse tree
	 */
	void enterDimension(AquaParser.DimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#dimension}.
	 * @param ctx the parse tree
	 */
	void exitDimension(AquaParser.DimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#conflict}.
	 * @param ctx the parse tree
	 */
	void enterConflict(AquaParser.ConflictContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#conflict}.
	 * @param ctx the parse tree
	 */
	void exitConflict(AquaParser.ConflictContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#stmts}.
	 * @param ctx the parse tree
	 */
	void enterStmts(AquaParser.StmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#stmts}.
	 * @param ctx the parse tree
	 */
	void exitStmts(AquaParser.StmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#control_stmt}.
	 * @param ctx the parse tree
	 */
	void enterControl_stmt(AquaParser.Control_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#control_stmt}.
	 * @param ctx the parse tree
	 */
	void exitControl_stmt(AquaParser.Control_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#repeat}.
	 * @param ctx the parse tree
	 */
	void enterRepeat(AquaParser.RepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#repeat}.
	 * @param ctx the parse tree
	 */
	void exitRepeat(AquaParser.RepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterFor_loop(AquaParser.For_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitFor_loop(AquaParser.For_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(AquaParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(AquaParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignFluid}
	 * labeled alternative in {@link AquaParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignFluid(AquaParser.AssignFluidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignFluid}
	 * labeled alternative in {@link AquaParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignFluid(AquaParser.AssignFluidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link AquaParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(AquaParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link AquaParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(AquaParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#mix}.
	 * @param ctx the parse tree
	 */
	void enterMix(AquaParser.MixContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#mix}.
	 * @param ctx the parse tree
	 */
	void exitMix(AquaParser.MixContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#incubate}.
	 * @param ctx the parse tree
	 */
	void enterIncubate(AquaParser.IncubateContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#incubate}.
	 * @param ctx the parse tree
	 */
	void exitIncubate(AquaParser.IncubateContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#sense}.
	 * @param ctx the parse tree
	 */
	void enterSense(AquaParser.SenseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#sense}.
	 * @param ctx the parse tree
	 */
	void exitSense(AquaParser.SenseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#sense_type}.
	 * @param ctx the parse tree
	 */
	void enterSense_type(AquaParser.Sense_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#sense_type}.
	 * @param ctx the parse tree
	 */
	void exitSense_type(AquaParser.Sense_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstExpr}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstExpr(AquaParser.ConstExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstExpr}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstExpr(AquaParser.ConstExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(AquaParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(AquaParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(AquaParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(AquaParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParExpr}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(AquaParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParExpr}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(AquaParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(AquaParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link AquaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(AquaParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(AquaParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(AquaParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AquaParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(AquaParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link AquaParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(AquaParser.IndexContext ctx);
}