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

package org.elasticsearch4idea.ui.explorer.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import org.elasticsearch4idea.model.Request
import org.elasticsearch4idea.ui.explorer.ElasticsearchExplorer

class RunQueryAction constructor(
    private val elasticsearchExplorer: ElasticsearchExplorer,
    private val requestProvider: () -> Request,
    private val isClusterAction: Boolean,
    text: String
) : DumbAwareAction(text) {

    constructor(
        elasticsearchExplorer: ElasticsearchExplorer,
        request: Request,
        isClusterAction: Boolean,
        text: String
    ) : this(elasticsearchExplorer, { request }, isClusterAction, text)

    override fun actionPerformed(e: AnActionEvent) {
        val cluster = if (isClusterAction) {
            elasticsearchExplorer.getSelectedCluster()
        } else {
            elasticsearchExplorer.getSelectedIndex()?.cluster
        }
        if (cluster != null) {
            elasticsearchExplorer.openQueryEditor(cluster, requestProvider.invoke(), 0.0f)
        }
    }

    override fun update(event: AnActionEvent) {
        if (isClusterAction) {
            val cluster = elasticsearchExplorer.getSelectedCluster()
            event.presentation.isVisible = cluster != null && cluster.isLoaded()
        } else {
            event.presentation.isVisible = elasticsearchExplorer.getSelectedIndex() != null
        }
    }
}