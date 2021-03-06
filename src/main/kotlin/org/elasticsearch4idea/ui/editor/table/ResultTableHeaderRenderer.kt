/*
 * Copyright 2020 Anton Shuvaev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elasticsearch4idea.ui.editor.table

import com.intellij.ui.JBColor
import com.intellij.util.ui.JBUI
import org.elasticsearch4idea.utils.MyUIUtils
import sun.swing.table.DefaultTableCellHeaderRenderer
import java.awt.Component
import javax.swing.JLabel
import javax.swing.JTable
import javax.swing.SwingConstants

class ResultTableHeaderRenderer : DefaultTableCellHeaderRenderer() {

    override fun getTableCellRendererComponent(
        table: JTable?,
        value: Any?,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component? {
        val cmp: Component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
        if (cmp !is JLabel) return cmp
        cmp.horizontalAlignment = SwingConstants.LEFT
        var border = cmp.border
        val indent = JBUI.Borders.empty(0, 8,  0, 0)
        border = JBUI.Borders.merge(border, indent, true)
        border = JBUI.Borders.merge(border, JBUI.Borders.customLine(JBColor.border(), 1, 0, 1, 1), true)
        cmp.border = border
        if (table?.isColumnSelected(column) == true) {
            cmp.background = MyUIUtils.getSelectedLineColor()
        }
        return cmp
    }
}