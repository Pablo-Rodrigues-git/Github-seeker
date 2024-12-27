package com.remotex.feature.presentation.screens.repositorylist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.remotex.commons.utils.extensions.CompactNumberFormatter.formatNumberToString
import com.remotex.feature.R
import com.remotex.domain.repositorylist.models.RepositoryModel
import com.remotex.domain.repositorylist.models.RepositoryOwnerModel

@Composable
fun ReposListItem(
    repositoryModel: RepositoryModel,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(width = 0.8.dp, color = MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                navController.navigate("pulls/${repositoryModel.owner.login}/${repositoryModel.name}")
            }
    ) {
        ListItem(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(130.dp),
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            overlineContent = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = repositoryModel.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            headlineContent = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = repositoryModel.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            },
            supportingContent = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    SupportingInfo(
                        onClick = { },
                        text = repositoryModel.forksCount.formatNumberToString(),
                        icon = painterResource(R.drawable.code_fork),
                        contentDescription = "Número de forks"
                    )
                    SupportingInfo(
                        onClick = { },
                        text = repositoryModel.stargazersCount.formatNumberToString(),
                        icon = painterResource(R.drawable.filled_star),
                        contentDescription = "Número de stars"
                    )
                }
            },
            trailingContent = {
                OwnerSection(repositoryModel.owner)
            }
        )
    }
}

@Composable
fun SupportingInfo(
    onClick: () -> Unit,
    text: String,
    icon: Painter,
    contentDescription: String
) {
    AssistChip(
        onClick = onClick,
        label = { Text(text) },
        leadingIcon = {
            Image(
                painter = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(AssistChipDefaults.IconSize)
            )
        },
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .padding(end = 16.dp)
    )
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun OwnerSection(owner: RepositoryOwnerModel) {
    Box(
        Modifier
            .fillMaxHeight()
            .padding(vertical = 12.dp)
            .width(120.dp)
    ) {
        Card(
            modifier = Modifier.align(Alignment.TopCenter).size(58.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            GlideImage(
                model = owner.avatarUrl,
                contentDescription = "Avatar de ${owner.login}"
            ) {
                it.diskCacheStrategy(DiskCacheStrategy.DATA)
            }
        }
        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = owner.login,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview(showBackground = false)
@Composable
fun ReposListItemPreview() {
    ReposListItem(
        RepositoryModel(
            name =  stringResource(R.string.github_api_android),
            description = LoremIpsum(15).values.joinToString(" "),
            stargazersCount = 100000,
            forksCount = 500000,
            owner = RepositoryOwnerModel(
                login = stringResource(R.string.test_user),
                avatarUrl = "https://avatars.githubusercontent.com/u/26586900?v=4"
            )
        ),
        rememberNavController()
    )
}