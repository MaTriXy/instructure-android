/*
 * Copyright (C) 2017 - present  Instructure, Inc.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, version 3 of the License.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.instructure.teacher.holders

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.instructure.canvasapi2.models.Quiz
import com.instructure.teacher.R
import kotlinx.android.synthetic.main.viewholder_header_expandable.view.*

class QuizExpandableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mIsExpanded = false

    fun bind(context: Context,
             isExpanded: Boolean,
             holder: QuizExpandableViewHolder,
             group: String,
             callback: (String) -> Unit) = with(itemView) {

        mIsExpanded = isExpanded
        var title = ""

        // the group name is currently the quiz type, but it's not very readable, so change it
        when(group) {
            Quiz.TYPE_ASSIGNMENT -> title = context.getString(R.string.assignmentQuizzes)
            Quiz.TYPE_SURVEY -> title = context.getString(R.string.surveys)
            Quiz.TYPE_GRADED_SURVEY -> title = context.getString(R.string.gradedSurveys)
            Quiz.TYPE_PRACTICE -> title = context.getString(R.string.practiceQuizzes)
        }

        groupName.text = title

        holder.itemView.setOnClickListener {
            val animationType = if (mIsExpanded) R.animator.rotation_from_0_to_neg90 else R.animator.rotation_from_neg90_to_0
            mIsExpanded = !mIsExpanded
            val flipAnimator = AnimatorInflater.loadAnimator(context, animationType) as ObjectAnimator
            flipAnimator.target = collapseIcon
            flipAnimator.duration = 200
            flipAnimator.start()
            callback(group)
        }
    }

    companion object {
        val holderResId: Int = R.layout.viewholder_header_expandable
    }
}
