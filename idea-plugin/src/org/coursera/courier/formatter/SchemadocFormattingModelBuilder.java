package org.coursera.courier.formatter;


import com.intellij.formatting.Block;
import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.FormattingModelProvider;
import com.intellij.formatting.Indent;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.alignment.AlignmentStrategy;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.coursera.courier.CourierLanguage;
import org.coursera.courier.schemadoc.psi.SchemadocTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SchemadocFormattingModelBuilder implements FormattingModelBuilder {
  @NotNull
  @Override
  public FormattingModel createModel(
    @NotNull PsiElement element, @NotNull CodeStyleSettings settings) {
    Block block =
      AbstractCourierBlock.createBlock(
        element.getNode(),
        Indent.getAbsoluteNoneIndent(),
        null,
        AlignmentStrategy.getNullStrategy(),
        settings,
        createSpacingBuilder(settings));

    return FormattingModelProvider.createFormattingModelForPsiFile(
      element.getContainingFile(), block, settings);
  }

  @NotNull
  private static SpacingBuilder createSpacingBuilder(@NotNull CodeStyleSettings settings) {
    return new SpacingBuilder(settings, CourierLanguage.INSTANCE)
      .before(SchemadocTypes.DOC_COMMENT_LEADING_ASTRISK).spaces(1)
      .after(SchemadocTypes.DOC_COMMENT_LEADING_ASTRISK).spaces(1)
      ;
  }

  @Nullable
  @Override
  public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
    return null;
  }
}
